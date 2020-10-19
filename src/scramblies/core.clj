(ns scramblies.core
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [ring.util.http-response :as http]
            [clojure.data.json :as json]
            [clojure.string :as str]))

(def only-small-letters-regex #"^[a-z_\\-]+$")

(defn allow-cross-origin
  "NOT MINE! taken from https://medium.com/coinmonks/how-to-fix-cors-web-server-error-6c2c55938f95"
  ([handler]
   (allow-cross-origin handler "*"))
  ([handler allowed-origins]
   (fn [request]
     (if (= (request :request-method) :options)
       (-> (http/ok)                     ; Don't pass the requests down, just return what the browser needs to continue.
           (assoc-in [:headers "Access-Control-Allow-Origin"] allowed-origins)
           (assoc-in [:headers "Access-Control-Allow-Methods"] "GET")
           (assoc-in [:headers "Access-Control-Allow-Headers"] "Content-Type,Cache-Control,Origin,Accept,Authorization")
           (assoc :status 200)
           )
       (-> (handler request)         ; Pass the request on, but make sure we add this header for CORS support in Chrome.
           (assoc-in [:headers "Access-Control-Allow-Origin"] allowed-origins))))))


(defn scramble?
  [str1 str2]
  (loop [s1 str1 s2 str2]
    (if (str/blank? s2)
      true
      (let [i (str/index-of s1 (get s2 0))]
        (if (nil? i)
          false
          (recur (str (subs s1 0 i) (subs s1 (inc i))) (subs s2 1)))))))

(defn valid-inputs?
  [s1 s2]
  (and
    (not (str/blank? s1))
    (not (str/blank? s2))
    (re-matches only-small-letters-regex s1)
    (re-matches only-small-letters-regex s2)))

(defn scramble-endpoint [req]
  (let [str1 (get (:params req) "str1")
        str2 (get (:params req) "str2")]
    (if (valid-inputs? str1 str2)
      {:status  200
       :headers {"Content-Type" "application/json" "Access-Control-Allow-Headers" "*"}
       :body    (json/write-str {:answer (scramble? (get (:params req) "str1") (get (:params req) "str2"))})}
      {:status  400
       :headers {"Content-Type" "application/json" "Access-Control-Allow-Headers" "*"}
       :body    (json/write-str {:error "Parameters str1 and str2 must both be present and contain just small letters."})})))

(defroutes app-routes
             (GET "/scramble" [] scramble-endpoint)
           (route/not-found
             {:status  501
              :headers {"Content-Type" "application/json" "Access-Control-Allow-Headers" "*"}
              :body    (json/write-str {:error "Only implemented endpoint is /scramble?str1=&str2="})}))

(def app
  (-> app-routes
      ring.middleware.params/wrap-params
      allow-cross-origin))

(defn -main
  "Main function"
  [& args]
  (server/run-server #'app {:port 6969})
  (println "Server has started on port 6969"))

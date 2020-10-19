(ns scramblies_ui.components
  (:require [reagent.core :as r]
            [ajax.core :refer [GET]]))


(defonce str1 (r/atom ""))
(defonce str2 (r/atom ""))

(def scramble-host-endpoint "http://localhost:6969/scramble")

(defn api-response-handler [response]
  (js/alert (get response "answer")))

(defn api-error-handler [err]
  (let [status (:status err)]
    (if (= status 400)
      (js/alert (get (:response err) "error"))
      (js/alert "Error getting response"))))

(defn header-component
  []
  [:h1
   "Check if the chars can be matched in main string"])

(defn input-element
  "An input element"
  [id name]
  {:id id
   :name name
   :class "word-input"
   :type "text"})

(def scramble-string-one
  (input-element "str1" "str1"))

(def scramble-string-two
  (input-element "str2" "str2"))

(defn two-button-component
  []
  [:div
   [:p "Main string:" [:input (assoc scramble-string-one :on-change #(reset! str1 (-> % .-target .-value)))]]
   [:p "Matching char squence:" [:input (assoc scramble-string-two :on-change #(reset! str2 (-> % .-target .-value)))]]
   [:p [:input {:type "button"
                :value "Check if scrambled"
                :class "butt-class"                         ; :D
                :on-click #(GET scramble-host-endpoint {:params {:str1 @str1 :str2 @str2}
                                                        :headers {"Access-Control-Allow-Origin" "*"} ; bad practice, not for production but perfect just for the assigment
                                                        :handler api-response-handler
                                                        :error-handler api-error-handler})}]]])
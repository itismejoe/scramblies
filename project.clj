(defproject scramblies "0.1.0-SNAPSHOT"
  :description "Assigment"
  :url "https://choose.me"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [http-kit "2.5.0"]                         ; Published by contributors, see CHANGELOG for details
                 [http-kit "2.1.19"]                        ; Legacy, published by @shenfeng
                 [ring/ring-defaults "0.3.2"]
                 [metosin/ring-http-response "0.9.0"]
                 [compojure "1.6.2"]
                 [javax.xml.bind/jaxb-api "2.3.0"]
                 [org.clojure/data.json "1.0.0"]
                 [org.clojure/clojurescript "1.10.238" :scope "provided"]
                 [reagent "0.7.0"]
                 [cljs-ajax "0.8.1"]]

  :plugins [[lein-figwheel "0.5.16"]
            [lein-cljsbuild "1.1.7" :exclusions [[org.clojure/clojure]]]]

  :main ^:skip-aot scramblies.core

  :cljsbuild {:builds
              [{:id           "dev"
                :source-paths ["src/scramblies_ui"]
                :figwheel     {:on-jsload "scramblies_ui.core/on-js-reload"
                               :open-urls ["http://localhost:3449/index.html"]}

                :compiler     {:main                 scramblies_ui.core
                               :asset-path           "js/compiled/out"
                               :output-to            "resources/public/js/compiled/site.js"
                               :output-dir           "resources/public/js/compiled/out"
                               :source-map-timestamp true}}]}

  :figwheel {:css-dirs ["resources/public/css"]}

  :profiles {:dev {:dependencies [[binaryage/devtools "0.9.9"]
                                  [figwheel-sidecar "0.5.16"]
                                  [org.clojure/data.json "1.0.0"]
                                  [cider/piggieback "0.3.1"]]
                   :source-paths ["dev"]
                   :clean-targets ^{:protect false} ["resources/public/js/compiled/"]}}

  :repl-options {:init-ns scramblies.core})

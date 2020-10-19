(ns scramblies_ui.core
  (:require [reagent.core :as reagent]
            [scramblies_ui.components :as cmp]))

(defn app-component
      []
      [:div
       [cmp/header-component]
       [cmp/two-button-component]])

(reagent/render-component [app-component]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
      (reagent/force-update-all))
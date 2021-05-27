(ns creditcard.authorization.main
  (:require [common.kafka.kafka :as c.kafka]
            [common.kafka.config :as c.config]
            [creditcard.authorization.logic :as a.logic]
            [clojure.data.json :as json]))


(defn- call-message [value]
  (let [content (json/read-str value)
        action (get content "action")]
    (when (= action "AUTHORIZATION") (a.logic/authorization content))
    (when (= action "CANCEL") (a.logic/cancel-authorization! content))))

(defn -main [& args]
  (c.kafka/consumer! (c.config/build-properties-consumer) "CREDITCARD_AUTHORIZATION" call-message))
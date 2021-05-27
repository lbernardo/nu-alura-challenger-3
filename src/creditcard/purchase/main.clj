(ns creditcard.purchase.main
  (:require
    [clojure.data.json :as json]
    [common.kafka.kafka :as c.kafka]
            [common.kafka.config :as c.config]))

(defn -main [& args]
  (let [value (rand-int 1000)
        content (json/write-str {:action "AUTHORIZATION" :value value})]
    (c.kafka/producer! (c.config/build-properties-producer) "CREDITCARD_AUTHORIZATION" content)))

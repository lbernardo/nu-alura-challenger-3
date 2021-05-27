(ns creditcard.antifraud.logic
  (:require [common.kafka.kafka :as c.kafka]
            [common.kafka.config :as c.config]
            [clojure.data.json :as json]))


(defn fraud? []
    (let [n (rand-int 99999)]
      (< n 69999)))

(defn- call-payment! [content]
  (println (get content "transaction") "não é fraude :)")
  (c.kafka/producer! (c.config/build-properties-producer) "CREDITCARD_PAYMENT" (json/write-str content)))
(defn- call-notification! [content]
  (println (get content "transaction") "é fraude :(")
  (c.kafka/producer! (c.config/build-properties-producer) "CREDITCARD_AUTHORIZATION" (json/write-str (assoc content "action" "CANCEL")))
  (c.kafka/producer! (c.config/build-properties-producer) "CREDITCARD_NOTIFICATION" (json/write-str {:message "Infelizamente não conseguimos processar o seu pedido!"})))

(defn check-antifraud [content]
  (if (not (fraud?)) (call-payment! content)
     (call-notification! content)))

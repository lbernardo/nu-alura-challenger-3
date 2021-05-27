(ns creditcard.notification.logic)

(defn print-notification [content]
  (println (get content "message")))

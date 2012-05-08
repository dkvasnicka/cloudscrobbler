(ns net.danielkvasnicka.cloudscrobbler.utils.Utils
	(:gen-class
      :methods 
          [
           #^{:static true} [getOnlyTracksFromMixSections [java.util.Collection] java.util.Collection]
           #^{:static true} [getListenTimeForNewestMix [java.util.Collection] java.util.Date] 
          ]
  ))

(defn -getOnlyTracksFromMixSections [sections]
  (map #(.getTrack %) (filter #(.isTrack %) sections)))

(defn -getListenTimeForNewestMix [mixes]
  (last (sort #(.getListenTime %) mixes)))

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package geofx

/**
 *
 * @author Mohamed Sanaulla
 */
class Geocode {
    def String latitude
    def String longitude
    def String locationType
    def String toString(){
        return "Latitude: ${latitude}, Longitude:${longitude} and Location type: ${locationType}"
    }
}


hasMany : Defines a one-to-many association between two classes.

class Playlist {

	String name
	//static hasMany = [songs: Song]
	Song song

    static constraints = {
    	name blank: false
    }

    String toString() {
        name
    }
}


package grails.static.scaffolding

import groovy.transform.CompileStatic

@CompileStatic
class BootStrap {

	SongService songService
	PlaylistService playlistService

    def init = { servletContext ->

    	Song testSong1 = songService.save('song1')
    	Song testSong2 = songService.save('song2')
    	Song testSong3 = songService.save('song3')
    	playlistService.save("playlist1", testSong1).save()
    	playlistService.save("playlist1", testSong2).save()
    	playlistService.save("playlist1", testSong3).save()

    }
    def destroy = {
    }
}




Description
By default GORM will create a property of type java.util.Set using the key inside the definition of the hasMany map. For example consider this definition:

static hasMany = [song: Song]
Here a property of type java.util.Set called songs will be created within the defining class. These can then be iterated over and manipulated:

def a = Playlist.get(1)
for (song in a.songs) { println song.title }

//read-only mode
def p = Person.read(1)

In this case the underlying Hibernate engine will not do any dirty checking and the object will not be persisted. Note that if you explicitly call the save method then the object is placed back into a read-write state.
In addition, you can also load a proxy for an instance by using the load method:
def p = Person.load(1)


class Playlist implements Comparable {
    	String title

	static constraints = {
	    	name blank: false
	    }

    	Date creationDate = new Date()

    	int compareTo(obj) {
        	creationDate.compareTo(obj.creationDate)
    	}
}


 unique constraint 
class Face {
    static hasOne = [nose:Nose]
    static constraints = {
        nose unique: true
    }
}

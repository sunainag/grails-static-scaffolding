package grails.static.scaffolding

import groovy.transform.CompileStatic

@CompileStatic
class BootStrap {

    def init = { servletContext ->

    	def testSong1 = new Song(title:"Kabhi Kabhi");
    	testSong1.save();

  //   	def song1 = Song.get(1)
		// assert 1 == song1.id

    	def music = new Playlist(name:"playlist1")
    	music.addToSongs(testSong1)
    	music.save()

    }
    def destroy = {
    }
}

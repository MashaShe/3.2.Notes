fun main (){
    val myNote1 = Note(id=1,
        ownerId =1,
        title = "Hello, my Note!",
        text = "my first text",
        date = 1610312400,
        comments = 0,
        readComments = 0
    )
    val myNote2 = Note(id=2,
        ownerId =1,
        title = "Hello, my second Note!",
        text = "my second text",
        date = 1610312487,
        comments = 1,
        readComments = 0
    )
    val myComment = Comment(id=1,
        noteId = 2,
        messageText = "My first comment to my first note!"
    )

    NotesService.add(myNote1)
    NotesService.add(myNote2)
    NotesService.createComment(myComment)
    NotesService.printNotes()
    NotesService.printComments()
}



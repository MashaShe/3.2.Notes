import NotesService.noteComments
import NotesService.notes
import org.junit.Test
import junit.framework.Assert.assertEquals

class NotesServiceTest {

    @org.junit.Test
    fun addTest() {
        val myNote1 = Note(
            id = 1,
            ownerId = 1,
            title = "Hello, my Note!",
            text = "my first text",
            date = 1610312400,
            comments = 0,
            readComments = 0
        )

        NotesService.add(myNote1)
        val result = notes[0].id !== 0
        val expected = true
        assertEquals(result, expected)
    }

    annotation class Test

    @org.junit.Test
    fun editExistingNoteTest() {
        val myNote1 = Note(
            id = 1,
            ownerId = 1,
            title = "Hello, my Note!",
            text = "my first text",
            date = 1610312400,
            comments = 0,
            readComments = 0
        )
        val myNote2 = Note(
            id = 1,
            ownerId = 1,
            title = "Hello, my second Note!",
            text = "my second text",
            date = 1610312487,
            comments = 1,
            readComments = 0
        )


        NotesService.add(myNote1)
        val result = NotesService.edit(myNote2)
        val expected = true
        assertEquals(result, expected)
    }

    @org.junit.Test(expected = NotesService.NoteNotFoundException::class)
    fun editNonExistingNoteTest() {
        val myNote1 = Note(
            id = 1,
            ownerId = 1,
            title = "Hello, my Note!",
            text = "my first text",
            date = 1610312400,
            comments = 0,
            readComments = 0
        )
        val myNote2 = Note(
            id = 20,
            ownerId = 1,
            title = "Hello, my second Note!",
            text = "my second text",
            date = 1610312487,
            comments = 1,
            readComments = 0
        )
        NotesService.add(myNote1)
        val result = NotesService.edit(myNote2)
        val expected = false
        assertEquals(result, expected)
    }

    @org.junit.Test(expected = NotesService.NoteNotFoundException::class)
    fun deleteNonExistingNoteTest() {
        val myNote1 = Note(
            id = 1,
            ownerId = 1,
            title = "Hello, my Note!",
            text = "my first text",
            date = 1610312400,
            comments = 0,
            readComments = 0
        )
        NotesService.add(myNote1)
        val result = NotesService.delete(70)
        val expected = false
        assertEquals(result, expected)

    }

    @org.junit.Test
    fun deleteExistingNoteTest() {
        val myNote1 = Note(
            id = 1,
            ownerId = 1,
            title = "Hello, my Note!",
            text = "my first text",
            date = 1610312400,
            comments = 0,
            readComments = 0
        )
        NotesService.add(myNote1)
        val result = NotesService.delete(myNote1.id)
        val expected = true
        assertEquals(result, expected)

    }

    @org.junit.Test(expected = NotesService.CommentNotFoundException::class)
    fun deleteNonExistingCommentTest() {
        val myNote1 = Note(
            id = 1,
            ownerId = 1,
            title = "Hello, my Note!",
            text = "my first text",
            date = 1610312400,
            comments = 0,
            readComments = 0
        )
        val myComment = Comment(
            id = 1,
            noteId = 1,
            messageText = "My first comment to my first note!"
        )
        NotesService.add(myNote1)
        NotesService.createComment(myComment)
        val result = NotesService.deleteComment(50)
        val expected = false
        assertEquals(result, expected)

    }


    @org.junit.Test
    fun deleteExistingCommentTest() {
        val myNote1 = Note(
            id = 1,
            ownerId = 1,
            title = "Hello, my Note!",
            text = "my first text",
            date = 1610312400,
            comments = 0,
            readComments = 0
        )
        val myComment = Comment(
            id = 1,
            noteId = 1,
            messageText = "My first comment to my first note!"
        )
        NotesService.add(myNote1)
        NotesService.createComment(myComment)
        val result = NotesService.deleteComment(myComment.id)
        val expected = true
        assertEquals(result, expected)

    }


    @org.junit.Test
    fun editExistingCommentTest() {
        val myNote1 = Note(
            id = 1,
            ownerId = 1,
            title = "Hello, my Note!",
            text = "my first text",
            date = 1610312400,
            comments = 0,
            readComments = 0
        )
        val myComment = Comment(
            id = 1,
            noteId = 1,
            messageText = "My first comment to my first note!"
        )
        val myComment2 = Comment(
            id = 1,
            noteId = 1,
            messageText = "My second comment to my first note!"
        )
        NotesService.add(myNote1)
        NotesService.createComment(myComment)
        val result = NotesService.editComment(myComment2)
        val expected = true
        assertEquals(result, expected)

    }


    @org.junit.Test(expected = NotesService.CommentNotFoundException::class)
    fun editNonExistingCommentTest() {
        val myNote1 = Note(
            id = 1,
            ownerId = 1,
            title = "Hello, my Note!",
            text = "my first text",
            date = 1610312400,
            comments = 0,
            readComments = 0
        )
        val myComment = Comment(
            id = 1,
            noteId = 1,
            messageText = "My first comment to my first note!"
        )
        val myComment2 = Comment(
            id = 70,
            noteId = 1,
            messageText = "My second comment to my first note!"
        )
        NotesService.add(myNote1)
        NotesService.createComment(myComment)
        NotesService.editComment(myComment2)

    }


    @org.junit.Test(expected = NotesService.NoteNotFoundException::class)
    fun commentsShouldThrow() {
        val myNote1 = Note(
            id = 1,
            ownerId = 1,
            title = "Hello, my Note!",
            text = "my first text",
            date = 1610312400,
            comments = 0,
            readComments = 0
        )
        val myComment = Comment(
            id = 1,
            noteId = 70,
            messageText = "My first comment to my first note!"
        )

        NotesService.add(myNote1)
        NotesService.createComment(myComment)

    }

    @org.junit.Test(expected = NotesService.CommentTooShortException::class)
    fun commentsShouldThrowLengthTest() {
        val myNote1 = Note(
            id = 1,
            ownerId = 1,
            title = "Hello, my Note!",
            text = "my first text",
            date = 1610312400,
            comments = 0,
            readComments = 0
        )
        val myComment1 = Comment(
            id = 1,
            noteId = 1,
            messageText = "My first comment to my first note!"
        )
        val myComment2 = Comment(
            id = 1,
            noteId = 70,
            messageText = "M"
        )


        NotesService.add(myNote1)
        NotesService.createComment(myComment1)
        NotesService.editComment(myComment2)

    }

    @org.junit.Test
    fun commentsShouldNotThrowTest() {
        val myNote1 = Note(
            id = 1,
            ownerId = 1,
            title = "Hello, my Note!",
            text = "my first text",
            date = 1610312400,
            comments = 0,
            readComments = 0
        )
        val myComment = Comment(
            id = 1,
            noteId = 1,
            messageText = "My first comment to my first note!"
        )

        NotesService.add(myNote1)
        NotesService.createComment(myComment)
        val result = noteComments.last().id
        val expected = 5
        assertEquals(result, expected)
    }


    @org.junit.Test
    fun getNotesTest() {

        val myNote1 = Note(
            id = 1,
            ownerId = 1,
            title = "Hello, my Note!",
            text = "my first text",
            date = 1610312400,
            comments = 0,
            readComments = 0
        )
        val myNote2 = Note(
            id = 2,
            ownerId = 1,
            title = "Hello, my Note!",
            text = "my second text",
            date = 1610312400,
            comments = 0,
            readComments = 0
        )

        NotesService.add(myNote1)
        NotesService.add(myNote2)
        val result = NotesService.get(IntArray(2, { 1;2 })).size
        val expected = 2
        assertEquals(result, expected)

    }


    @org.junit.Test
    fun getNoteCommentsTest() {

        val myNote1 = Note(
            id = 1,
            ownerId = 1,
            title = "Hello, my Note!",
            text = "my first text",
            date = 1610312400,
            comments = 0,
            readComments = 0
        )
        val myComment1 = Comment(
            id = 1,
            noteId = 1,
            messageText = "My first comment to my first note!"
        )
        val myComment2 = Comment(
            id = 2,
            noteId = 1,
            messageText = "My first comment to my first note!"
        )

        NotesService.add(myNote1)
        NotesService.createComment(myComment1)
        NotesService.createComment(myComment2)
        val result = NotesService.getComments(1).size
        val expected = 6
        assertEquals(result, expected)

    }


    @org.junit.Test
    fun getByIdTest() {
        val myNote1 = Note(
            id = 1,
            ownerId = 1,
            title = "Hello, my Note!",
            text = "my first text",
            date = 1610312400,
            comments = 0,
            readComments = 0
        )
        val myNote2 = Note(
            id = 2,
            ownerId = 1,
            title = "Hello, my Note!",
            text = "my second text",
            date = 1610312400,
            comments = 0,
            readComments = 0
        )

        NotesService.add(myNote1)
        val expected = NotesService.add(myNote2)
        val result = NotesService.getById(myNote2.id).id
        assertEquals(result, expected)
    }


    @org.junit.Test
    fun restoreCommentTest() {
        val myNote1 = Note(
            id = 1,
            ownerId = 1,
            title = "Hello, my Note!",
            text = "my first text",
            date = 1610312400,
            comments = 0,
            readComments = 0
        )
        val myComment = Comment(
            id = 1,
            noteId = 1,
            messageText = "My first comment to my first note!"
        )

        NotesService.add(myNote1)
        NotesService.createComment(myComment)
        NotesService.deleteComment(1)
        val result = NotesService.restoreComment(1)
        val expected = true
        assertEquals(result, expected)

    }
}



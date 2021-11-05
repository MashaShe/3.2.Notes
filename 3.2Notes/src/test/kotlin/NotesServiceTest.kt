package src.main.kotlin
import Note
import Comment
import NotesService
import org.junit.Test
import junit.framework.Assert.assertEquals

class NotesServiceTest {
    @org.junit.Test
    fun add() {
        val myNote1 = Note(
            id = 1,
            ownerId = 1,
            title = "Hello, my Note!",
            text = "my first text",
            date = 1610312400,
            comments = 0,
            readComments = 0
        )
        val notesService = NotesService()

        notesService.add(myNote1)
        val result = notesService.notes[0].id !== 0
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
        val notesService = NotesService()


        notesService.add(myNote1)
        val result = notesService.edit(myNote2)
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
        val notesService = NotesService()

        notesService.add(myNote1)
        val result = notesService.edit(myNote2)
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
        val notesService = NotesService()

        notesService.add(myNote1)
        val result = notesService.delete(70)
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
        val notesService = NotesService()

        notesService.add(myNote1)
        val result = notesService.delete(myNote1.id)
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
        val notesService = NotesService()

        notesService.add(myNote1)
        notesService.createComment(myComment)
        val result = notesService.deleteComment(50)
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
        val notesService = NotesService()

        notesService.add(myNote1)
        notesService.createComment(myComment)
        val result = notesService.deleteComment(myComment.id)
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
        val notesService = NotesService()

        notesService.add(myNote1)
        notesService.createComment(myComment)
        val result = notesService.editComment(myComment2)
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
        val notesService = NotesService()

        notesService.add(myNote1)
        notesService.createComment(myComment)
        notesService.editComment(myComment2)

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
        val notesService = NotesService()

        notesService.add(myNote1)
        notesService.createComment(myComment)

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
        val notesService = NotesService()


        notesService.add(myNote1)
        notesService.createComment(myComment1)
        notesService.editComment(myComment2)

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

        val notesService = NotesService()

        notesService.add(myNote1)
        notesService.createComment(myComment)
        val result = notesService.noteComments.last().id
        val expected = 1
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

        val notesService = NotesService()

        notesService.add(myNote1)
        notesService.add(myNote2)
        val result = notesService.get(IntArray(2, { 1;2 })).size
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
        val notesService = NotesService()

        notesService.add(myNote1)
        notesService.createComment(myComment1)
        notesService.createComment(myComment2)
        val result = notesService.getComments(1).size
        val expected = 2
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
        val notesService = NotesService()

        notesService.add(myNote1)
        val expected = notesService.add(myNote2)
        val result = notesService.getById(myNote2.id).id
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

        val notesService = NotesService()

        notesService.add(myNote1)
        notesService.createComment(myComment)
        notesService.deleteComment(1)
        val result = notesService.restoreComment(1)
        val expected = true
        assertEquals(result, expected)

    }
}



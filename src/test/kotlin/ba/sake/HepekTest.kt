package ba.sake

import ba.sake.hepek.bootstrap4.component.BootstrapFormComponents
import ba.sake.hepek.html.HtmlPage
import ba.sake.hepek.html.PageSettings
import kotlinx.html.*
import org.junit.Assert.assertTrue
import org.junit.Test
import java.nio.file.Files
import java.nio.file.Paths

class HepekTest {

    @Test
    fun testHepekContents() {
        val content = Index("Sake").contents().trim()
        Files.write(Paths.get("index.html"), content.toByteArray())
        println(content)
        assertTrue(1 == 1)
    }
}


class Index(val title: String) : HtmlPage {

    override fun styleURLs(): List<String> {
        return super.styleURLs() + listOf("https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css")
    }

    val Form = BootstrapFormComponents(BootstrapFormComponents.Companion.Type.Companion.Horizontal(1, 2))
    //val Form = BootstrapFormComponents(BootstrapFormComponents.Companion.Type.Companion.Inline)

    override fun pageSettings(): PageSettings {
        return super.pageSettings().copy(title = title)
    }

    override fun BODY.pageContent() {
        h1 { +"Selam merhaba!" }
        form {
            Form.inputText(this, "field1", "id1", "Text", Form.Success())
            Form.inputPassword(this, "field2", "id2", "Password", Form.Error())
            Form.inputEmail(this, "field3", "id3", "Email", Form.Warning())
            Form.inputUrl(this, "field4", "id4", "URL", Form.Warning())
            Form.inputTel(this, "field5", "id5", "Telephone", Form.Warning())
            Form.inputFile(this, "field6", "id6", "File", Form.Warning())
            Form.inputColor(this, "field7", "id7", "Color", Form.Warning())
            Form.inputNumber(this, "field8", "id8", "Number", Form.Warning())
            Form.inputRange(this, "field9", "id9", "Range", Form.Warning())
            Form.inputTime(this, "field10", "id10", "Time", Form.Warning())
            Form.inputWeek(this, "field11", "id11", "Week", Form.Warning())
            Form.inputMonth(this, "field12", "id12", "Month", Form.Warning())
            Form.inputDate(this, "field13", "id13", "Date", Form.Warning())
            Form.inputDateTimeLocal(this, "field14", "id1", "Datetime", Form.Warning())

            Form.inputSubmit(this, "Submit") {
                classes += setOf("btn-primary")
            }
            Form.inputButton(this, "dugme", "Button")
            Form.inputReset(this, "Reset")

        }
    }
}

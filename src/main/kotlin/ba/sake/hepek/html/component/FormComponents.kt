package ba.sake.hepek.html.component

import kotlinx.html.*

// fun <T : FORM> T.constructInputNormal()
// u KONTEKSTU FORM-e možeš pozvat constructInputNormal
// znači ovako samo mora: form { constructInputNormal{} }

abstract class FormComponents {
    companion object {
        interface Type {
            fun classes(): List<String> = emptyList()
        }

        interface ValidationStateClasses {
            fun success() = "success"
            fun warning() = "warning"
            fun error() = "error"
        }

        // possible states of input field
        data class ValidationState(val classes: String)
    }

    fun Success() = ValidationState(validationStateClasses().success())
    fun Warning() = ValidationState(validationStateClasses().warning())
    fun Error() = ValidationState(validationStateClasses().error())

    abstract fun validationStateClasses(): ValidationStateClasses

    abstract fun formType(): Type

    fun <T : FlowContent> T.form(block: FORM.() -> Unit) {
        val formClasses = formType().classes().joinToString(" ")
        form(classes = formClasses) { block(this) }
    }

    fun <T : FORM> inputText(
            form: T,
            name: String,
            id: String? = null,
            label: String? = null,
            state: ValidationState? = null,
            block: INPUT.() -> Unit = {}
    ) = form.constructInputNormal(InputType.text, name, id, label, state, block)

    fun <T : FORM> inputPassword(
            form: T,
            name: String,
            id: String? = null,
            label: String? = null,
            state: ValidationState? = null,
            block: INPUT.() -> Unit = {}
    ) = form.constructInputNormal(InputType.password, name, id, label, state, block)

    fun <T : FORM> inputEmail(
            form: T,
            name: String,
            id: String? = null,
            label: String? = null,
            state: ValidationState? = null,
            block: INPUT.() -> Unit = {}
    ) = form.constructInputNormal(InputType.email, name, id, label, state, block)

    fun <T : FORM> inputUrl(
            form: T,
            name: String,
            id: String? = null,
            label: String? = null,
            state: ValidationState? = null,
            block: INPUT.() -> Unit = {}
    ) = form.constructInputNormal(InputType.url, name, id, label, state, block)

    fun <T : FORM> inputTel(
            form: T,
            name: String,
            id: String? = null,
            label: String? = null,
            state: ValidationState? = null,
            block: INPUT.() -> Unit = {}
    ) = form.constructInputNormal(InputType.tel, name, id, label, state, block)

    fun <T : FORM> inputFile(
            form: T,
            name: String,
            id: String? = null,
            label: String? = null,
            state: ValidationState? = null,
            block: INPUT.() -> Unit = {}
    ) = form.constructInputNormal(InputType.file, name, id, label, state, block)

    fun <T : FORM> inputColor(
            form: T,
            name: String,
            id: String? = null,
            label: String? = null,
            state: ValidationState? = null,
            block: INPUT.() -> Unit = {}
    ) = form.constructInputNormal(InputType.color, name, id, label, state, block)

    fun <T : FORM> inputNumber(
            form: T,
            name: String,
            id: String? = null,
            label: String? = null,
            state: ValidationState? = null,
            block: INPUT.() -> Unit = {}
    ) = form.constructInputNormal(InputType.number, name, id, label, state, block)

    fun <T : FORM> inputRange(
            form: T,
            name: String,
            id: String? = null,
            label: String? = null,
            state: ValidationState? = null,
            block: INPUT.() -> Unit = {}
    ) = form.constructInputNormal(InputType.range, name, id, label, state, block)

    fun <T : FORM> inputTime(
            form: T,
            name: String,
            id: String? = null,
            label: String? = null,
            state: ValidationState? = null,
            block: INPUT.() -> Unit = {}
    ) = form.constructInputNormal(InputType.time, name, id, label, state, block)

    fun <T : FORM> inputWeek(
            form: T,
            name: String,
            id: String? = null,
            label: String? = null,
            state: ValidationState? = null,
            block: INPUT.() -> Unit = {}
    ) = form.constructInputNormal(InputType.week, name, id, label, state, block)

    fun <T : FORM> inputMonth(
            form: T,
            name: String,
            id: String? = null,
            label: String? = null,
            state: ValidationState? = null,
            block: INPUT.() -> Unit = {}
    ) = form.constructInputNormal(InputType.month, name, id, label, state, block)

    fun <T : FORM> inputDate(
            form: T,
            name: String,
            id: String? = null,
            label: String? = null,
            state: ValidationState? = null,
            block: INPUT.() -> Unit = {}
    ) = form.constructInputNormal(InputType.date, name, id, label, state, block)

    fun <T : FORM> inputDateTimeLocal(
            form: T,
            name: String,
            id: String? = null,
            label: String? = null,
            state: ValidationState? = null,
            block: INPUT.() -> Unit = {}
    ) = form.constructInputNormal(InputType.dateTimeLocal, name, id, label, state, block)

    fun <T : FORM> inputSubmit(form: T, value: String, block: INPUT.() -> Unit = {}) =
            form.constructInputButton(InputType.submit, value, value, block)

    fun <T : FORM> inputButton(form: T, label: String? = null, block: INPUT.() -> Unit = {}) =
            form.constructInputButton(InputType.button, label, label, block)

    fun <T : FORM> inputReset(form: T, value: String, block: INPUT.() -> Unit = {}) =
            form.constructInputButton(InputType.reset, value, value, block)

    /* CONSTRUCTORS */
    protected abstract fun <T : FORM> T.constructInputNormal(
            inputType: InputType,
            inputName: String,
            inputId: String?,
            inputLabel: String?,
            inputValidationState: ValidationState?,
            block: INPUT.() -> Unit
    )

    protected abstract fun <T : FORM> T.constructInputButton(
            inputType: InputType,
            inputValue: String?,
            inputLabel: String?,
            block: INPUT.() -> Unit = {}
    )

}
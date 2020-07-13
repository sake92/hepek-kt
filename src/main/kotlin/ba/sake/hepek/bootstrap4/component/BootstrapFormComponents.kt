package ba.sake.hepek.bootstrap4.component

import ba.sake.hepek.html.component.FormComponents
import kotlinx.html.*

data class BootstrapFormComponents(
        val formType: FormComponents.Companion.Type = Type.Companion.Vertical
) : FormComponents() {
    companion object {
        private val InputClasses = "form-control"
        private val BtnClasses = "btn"

        interface Type : FormComponents.Companion.Type {
            companion object {
                object Vertical : Type

                object Inline : Type {
                    override fun classes() = listOf("form-inline")
                }

                class Horizontal(val labelRatio: Int = 1, val inputRatio: Int = 3) : Type {
                    override fun classes() = listOf("form-horizontal")
                }
            }
        }


        object BootstrapValidationStateClasses : FormComponents.Companion.ValidationStateClasses {
            override fun success() = "has-success"
            override fun warning() = "has-warning"
            override fun error() = "has-error"
        }
    }

    override fun validationStateClasses() = BootstrapValidationStateClasses

    override fun formType() = formType

    override fun <T : FORM> T.constructInputNormal(inputType: InputType, inputName: String, inputId: String?, inputLabel: String?, inputValidationState: FormComponents.Companion.ValidationState?, block: INPUT.() -> Unit) {
        val inputValidationCls = inputValidationState?.classes ?: ""
        when (formType) {
            is Type.Companion.Horizontal -> {
                val (colLabel, colInput) = horizontalRatioClasses(formType, true)
                formGroup("$inputValidationCls row") {
                    label(classes = "col-form-label text-right $colLabel") {
                        if (!inputId.isNullOrBlank()) htmlFor = inputId
                        if (!inputLabel.isNullOrBlank()) +inputLabel
                    }
                    div(classes = colInput) {
                        input(classes = InputClasses, type = inputType, name = inputName) {
                            block(this)
                            if (!inputId.isNullOrBlank()) id = inputId
                        }
                    }
                }
            }
            else ->
                formGroup(inputValidationCls) {
                    label {
                        if (!inputId.isNullOrBlank()) htmlFor = inputId
                        if (!inputLabel.isNullOrBlank()) +inputLabel
                    }
                    input(classes = InputClasses, type = inputType, name = inputName) {
                        block(this)
                        if (!inputId.isNullOrBlank()) id = inputId
                    }
                }
        }
    }

    override fun <T : FORM> T.constructInputButton(inputType: InputType, inputValue: String?, inputLabel: String?, block: INPUT.() -> Unit) {
        when (formType) {
            is Type.Companion.Horizontal -> {
                val (colLabel, colInput) = horizontalRatioClasses(formType, false)
                formGroup("row") {
                    div(classes = "$colLabel $colInput") {
                        if (inputType == InputType.button) button(classes = BtnClasses) { if (!inputLabel.isNullOrBlank()) +inputLabel }
                        else input(type = inputType, classes = BtnClasses) {
                            block(this)
                            if (!inputValue.isNullOrBlank()) value = inputValue
                        }
                    }
                }
            }
            else ->
                if (inputType == InputType.button) button(classes = BtnClasses) { if (!inputLabel.isNullOrBlank()) +inputLabel }
                else input(type = inputType, classes = BtnClasses) {
                    block(this)
                    if (!inputValue.isNullOrBlank()) value = inputValue
                }
        }
    }

    override fun <T : FORM> T.constructInputCheckbox(
            inputName: String,
            inputLabel: String?,
            block: INPUT.() -> Unit
    ) {
        val label = " $inputLabel"
        when (formType) {
            is Type.Companion.Horizontal -> {
                val (colLabel, colInput) = horizontalRatioClasses(formType, false)
                formGroup("row") {
                    div(classes = "$colLabel $colInput") {
                        label {
                            input(type = InputType.checkBox, name = inputName) {
                                if (!inputLabel.isNullOrBlank()) +label
                            }
                        }
                    }
                }
            }
            else -> {
                label {
                    input(type = InputType.checkBox, name = inputName) {
                        if (!inputLabel.isNullOrBlank()) +label
                    }
                }
            }
        }
    }

    override fun <T : FORM> T.constructInputCheckboxes(inputName: String, labels: List<String>, inputLabel: String?, inline: Boolean, block: INPUT.() -> Unit) {
        val label = " $inputLabel"
        when (formType) {
            is Type.Companion.Horizontal -> {
                val (colLabel, colInput) = horizontalRatioClasses(formType, true)
                formGroup("row") {
                    label(classes = "text-right $colLabel") { if (!inputLabel.isNullOrBlank()) +label }
                    div(classes = colInput) {
                        labels.forEach { cbLabel ->
                            div(classes = "form-check") {
                                label {
                                    input(type = InputType.checkBox, name = inputName) {
                                        if (!cbLabel.isBlank()) +" $cbLabel"
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else -> {
                labels.forEach { cbLabel ->
                    label {
                        input(type = InputType.checkBox, name = inputName) {
                            if (!cbLabel.isBlank()) +" $cbLabel"
                        }
                    }
                }
            }
        }
    }

    private fun <T : FORM> T.formGroup(classes: String = "", block: DIV.() -> Unit) =
            div(classes = "form-group $classes") { block(this) }

    private fun horizontalRatioClasses(ht: Type.Companion.Horizontal, hasLabel: Boolean): Pair<String, String> {
        val labelRatio = ((ht.labelRatio / (ht.labelRatio + ht.inputRatio).toDouble()) * 12).toInt()
        val inputRatio = ((ht.inputRatio / (ht.labelRatio + ht.inputRatio).toDouble()) * 12).toInt()
        val lblCls = if (hasLabel) "col-sm" else "offset-sm"
        return ("$lblCls-$labelRatio" to "col-sm-$inputRatio")
    }
}
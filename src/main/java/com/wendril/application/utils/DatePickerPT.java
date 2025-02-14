package com.wendril.application.utils;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.datepicker.DatePicker;

import java.io.Serial;
import java.util.List;

public class DatePickerPT extends DatePicker {
    @Serial
    private static final long serialVersionUID = 5201333412700891178L;
    private final String id = "id-" + this.hashCode();

    public DatePickerPT(String label) {
        super(label);
        this.setId(id);

        defineI18n();

        setAutoOpen(false);
        formataData();
    }

    private void defineI18n() {
        DatePickerI18n ptBrI18n = new DatePickerI18n();
        ptBrI18n.setMonthNames(List.of("Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"));
        ptBrI18n.setWeekdays(List.of("Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"));
        ptBrI18n.setWeekdaysShort(List.of("Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sáb"));
        ptBrI18n.setToday("Hoje");
        ptBrI18n.setCancel("Cancelar");
        setI18n(ptBrI18n);
    }

    public void formataData() {
        UI ui = UI.getCurrent();

        ui.getPage().executeJs(getScript(this.id));
    }

    public String getScript(String setId) {
        return "var inputDoDatePicker = document.querySelector(\"#" + setId + " > input\");" +
                "var teclasPermitidas = ['Backspace', 'Delete', 'ArrowRight', 'ArrowLeft', 'Tab'];" +
                "var regexNumeros = /^\\d$/;" +
                "function validarTecla(event) {" +
                "    var valor = inputDoDatePicker.value;" +
                "    if (!regexNumeros.test(event.key) && teclasPermitidas.indexOf(event.key) === -1) {" +
                "        event.preventDefault();" +
                "    } else if (valor.length >= 10 && regexNumeros.test(event.key)) {" +
                "        inputDoDatePicker.value = '';" +
                "    }" +
                "}" +
                "function validarInput(event) {" +
                "    var valor = inputDoDatePicker.value;" +
                "    if (event.inputType !== 'deleteContentBackward' && event.inputType !== 'insertFromPaste') {" +
                "        valor = formatarData(valor);" +
                "    } " +
                "    inputDoDatePicker.value = valor;" +
                "}" +
                "function formatarData(valor) {" +
                "    if (valor.length === 2) {" +
                "        var dia = parseInt(valor);" +
                "        if (dia > 31) {" +
                "            valor = '31';" +
                "        }" +
                "        valor += '/';" +
                "    } else if (valor.length === 5) {" +
                "        var mes = parseInt(valor.substring(3, 5));" +
                "        if (mes > 12) {" +
                "            valor = valor.substring(0, 3) + '12';" +
                "        }" +
                "        valor += '/';" +
                "    } else if (valor.length === 10) {" +
                "        valor = validarDataCompleta(valor);" +
                "    }" +
                "    return valor;" +
                "}" +
                "function validarDataCompleta(valor) {" +
                "    var dia = parseInt(valor.substring(0, 2));" +
                "    var mes = parseInt(valor.substring(3, 5));" +
                "    var ano = parseInt(valor.substring(6, 10));" +
                "    if (ano < 1000) {" +
                "        ano = 1000;" +
                "    }" +
                "    var maxDia = calcularMaxDia(mes, ano);" +
                "    if (dia > maxDia) {" +
                "        dia = maxDia;" +
                "    }" +
                "    return ('0' + dia).slice(-2) + '/' + ('0' + mes).slice(-2) + '/' + ano;" +
                "}" +
                "function calcularMaxDia(mes, ano) {" +
                "    var maxDia = 31;" +
                "    if ([4, 6, 9, 11].indexOf(mes) !== -1) {" +
                "        maxDia = 30;" +
                "    } else if (mes == 2) {" +
                "        maxDia = ano % 4 == 0 && (ano % 100 != 0 || ano % 400 == 0) ? 29 : 28;" +
                "    }" +
                "    return maxDia;" +
                "}" +
                "function validarDataAoSair() {" +
                "    var valor = inputDoDatePicker.value;" +
                "        var datePickerInputField = document.querySelector(\"#" + setId + "\").shadowRoot.querySelector(\"div > vaadin-input-container\"); " +
                "     if (valor.length !== 10 || valor.charAt(2) !== '/' || valor.charAt(5) !== '/') {" +
                "        inputDoDatePicker.value = '';" +
                "        datePickerInputField.style.backgroundColor = '#FCE8E7';" +
                "    } else {" +
                "        datePickerInputField.style.backgroundColor = '#DDE1E7';" +
                "    }" +
                "}" +
                "inputDoDatePicker.addEventListener('keydown', validarTecla);" +
                "inputDoDatePicker.addEventListener('input', validarInput);" +
                "inputDoDatePicker.addEventListener('blur', validarDataAoSair);";
    }
}

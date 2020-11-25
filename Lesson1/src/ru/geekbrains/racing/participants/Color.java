package ru.geekbrains.racing.participants;

public enum Color {
    BLACK("Black", "Черный"),
    GREY("Grey", "Серый"),
    WHITE("White", "Белый"),
    RED("Orange", "Рыжий");

    private String englishColorName;
    private String russianColorName;

    Color(String englishColorName, String russianColorName) {
        this.englishColorName = englishColorName;
        this.russianColorName = russianColorName;
    }

    public String getEnglishColorName() {
        return englishColorName;
    }

    public String getRussianColorName() {
        return russianColorName;
    }
}

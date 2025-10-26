# Финальное задание FHW_UI


## Тесты:

1. Авторизация в системе
2. Переход в проект Test
3. Проверка увеличения счётчика задач при создании новой
4. Проверка статуса и версии задачи **TestSeleniumATHomework**
5. Создание нового бага с заполнением полей
6. Перевод созданного бага по workflow до статуса **Закрыт**

### Тесты можно запустить:

Через класс FullScenarioTest, целиком или каждый отдельно.

Через консоль

`mvn clean test`

### Для создания отчета в консоли прописать:

`mvn allure:report`

`mvn allure:serve`

## Скриншоты отчета allure

![image](https://github.com/t2t2t2t/ImageForReadMe/blob/main/imageHW/main.jpg)

![image](https://github.com/t2t2t2t/ImageForReadMe/blob/main/imageHW/behaviors.jpg)

## Использовалось:

- **Selenide** для работы с веб-интерфейсом
- **JUnit 5** фреймворка для запуска тестов
- **Allure Report** для генерации отчётов
- **XPath** локаторы

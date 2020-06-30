## Sber_chess

### Задача:
#### Шахматисты. Школы. Рейтинги Эло. 
#####    1. Spring. WEB-приложение. БД.
* Каждые 30 секунд проводится партия между двумя случайными шахматистами разных школ. По результату (победитель выбирается случайным образом) рассчитывается их рейтинг Эло;
* Для шахматистов CRD (Из слова CRUD). R в виде списка;
* Отображение списка пяти шахматистов с лучшим изменением рейтинга за последние 5 минут;
#####    2. Библиотека для мониторинга(jar) содержит в себе и обрабатывает аннотацию @Monitoring(“EVENT_NAME")
* Подключить во все «важные» методы приложения, которое обсуждалось выше в пункте 1.
* Аннотация собирает 3 метрики по методу: факт входа в метод (…_START), факт завершения метода (…_END), длительность выполнение метода в миллисекундах(…_DURATION)


### Тайм-лог:
* 1,5 часа на создание каркаса приложения, создание моделей, контроллеров, интерфейсов Jpa, создание скриптов для liquebase
* 1,5 часа на чтение про рейтинг ЭЛО, написание упрощённого алгоритма вычислений, если будет время - необходимо усложнить алгоритм до настоящего (расчёт К, вариант с ничьёй)

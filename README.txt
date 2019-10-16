Website Monitoring Tool

При разработке данного проекта были реализованы back-end и front-end для мониторинга URL.

Для разработки использовался язык программирования Java с использованием фреймворка JUnit для тестирования.

Для реализации веб части был использован стек технологий Java EE (Servlets, JSP).

В качестве сервера был использован Tomcat 9.

Для сборки проекта использовался Maven.

Среда разработки где был реализован данный проект - IntelliJ Idea Ultimate.

Для баз данных был использован SQL и MySQL Workbench.

Операционная система машины - Windows 10.

Проект содержит два основных пакета: Model, Controller и реализует MVC модель.

Пакет Model - реализует бизнес логику приложения. В себе содержит: пакет Data и классы (
											Monitor
											MonitoredURL
											MonitoringResult
											MonitorsList
											Status
											StatusMessage
											);

Пакет Data представляет собой набор из 3 классов: MonitoringDataStorage, FileMonitoringDataStorage, 
DatabaseMonitoringDataStorage и реализует подключение и работу с данными.

DatabaseMonitoringDataStorage был реализован с реализацией Singltone паттерна проектирования.

Пакет Controller - реализует общение между моделью и front-end частью проекта и содержит классы (
											AdderServlet
											EditorServlet
											InitialDataServlet
											MonitoredDataServlet
											RemoverServlet
											UdaterServlet).

В папке webapp находятся файлы для реализации пользовательской части: JSP файлы, аудио файлы, web.xml.
Так же в папку ресурсов был добавлен sql script.

Помимо файлов необходимых для работы проекта существует папка test/java где находится класс Test
который рассчитан на проведение базовых тестов.

Для тестирования приложения были использованы следующие URL:
https://www.multitran.com/
https://www.youtube.com/
https://www.i.ua/

Класс Monitor собирает информацию по одному из URL в отдельном потоке.

MonitorsList - содержит в себе мониторы для каждого URL с ключом URL.

В пакете Controller реализованы классы с редактированием, удалением, добавлением и отображением URL.

MonitoredDataServlet - запускает список мониторов и передает их в jsp файл - monitoringTable.

monitoringTable перезапускает MonitoredDataServlet каждые 5 секунд тем самым обновляя данные про URL.


Инструкция:
	Для установки Maven.
1) Скачать с официального сайта архив.
2) Распаковать архив в любую директорию.
3) Создать переменные среды: M2_HOME, JAVA_HOME, JRE_HOME.
	Для установки tomcat.
1) Скачать с официального сайта архив.
2) Распаковать архив в любую директорию.
	Для получения war файла.
1) Перейти в каталог где находится pom.xml файл приложения.
2) В консоли ввести mvn clean install для компиляции проекта.
3) mvn dependency:copy-dependencies - команда для сборки всех нужных плагинов.
	Для запуска приложения.
1) Поместить скомпилированый war архив в папку webapps которая находится в папке с сервером - appache-tomcat
2) После запуска сервера перейти в браузере по адресу http://localhost:8080/WebsiteMonitoringTool/
	Для запуска сервера.
1) запустить файл startup.bat/startup.sh, который находится в папке с сервером appache-tomcat/bin
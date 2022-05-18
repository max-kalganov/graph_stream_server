PORT = 8008

CLASS_FILE = Server.class

.all: run_server compile_server clean_compile
.PHONY: run_server compile_server clean_compile
.SILENT: run_server compile_server clean_compile $(CLASS_FILE)

run_server: compile_server
	echo "Running server"
	java -cp classpaths/gs-core-2.0.jar:classpaths/gs-algo-2.0.jar:classpaths/gs-ui-swing-2.0.jar:. Server.java $(PORT)

compile_server: $(CLASS_FILE)

$(CLASS_FILE):
	echo "Compiling server"
	javac -cp classpaths/gs-core-2.0.jar:classpaths/gs-algo-2.0.jar:classpaths/gs-ui-swing-2.0.jar:. Server.java

clean_compile:
	echo "Removing .class file"
	rm Server.class

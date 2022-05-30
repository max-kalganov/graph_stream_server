PORT = 8080

CLASS_FILE = Server.class

.all: run_server compile_server clean
.PHONY: run_server compile_server clean
.SILENT: run_server compile_server clean $(CLASS_FILE)

ifeq ($(OS),Windows_NT)
  include MakefileWin	
else
  include MakefileUnix
endif
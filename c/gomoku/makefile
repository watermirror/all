SOURCES=\
	ui/terminal_gfx.c\
	ui/terminal_input.c\
	test/ui/test_main.c\

BUILD_CMD=gcc -o out/gomoku $(SOURCES) -I ./

out/gomoku: $(SOURCES) out
	$(BUILD_CMD)

out:
	mkdir out

rebuild: clean out
	$(BUILD_CMD)

clean:
	rm -rf out

run:
	out/gomoku
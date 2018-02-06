ps -e | grep peopleana | grep -v grep | cut -c 1-6 | xargs kill -s SIGKILL


ps -e | grep nginx | grep -v grep | cut -c 1-6 | xargs kill -s INT


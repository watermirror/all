# Run peopleana*.jar via port 8080 ~ 8089

import os

for i in range(0, 10) :
	os.system("java -jar target/peopleana-0.0.1-SNAPSHOT.jar --server.port=808" + str(i) +
		" > temp_out/" + str(i) + ".out &");

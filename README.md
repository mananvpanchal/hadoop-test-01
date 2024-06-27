* To create cluster 
  - `docker-compose up -d` 
* To just start and stop
  - `docker-compose start`
  - `docker-compose stop`
  
* Connect namenode using
  - `winpty docker exec -it <dir_name_of_compose_file>_namenode_1 bash` (Windows)
  - `cd /home`
  - `sudo mkdir <dir_name>`
* To move data files to hdfs, first move it into namenode
  - `docker cp <src_file_path> apache-hadoop_namenode_1:/home/<dir_name>`
  - `hdfs dfs -mkdir -p <data_dir>`
  - `hdfs dfs -copyFromLocal <data_file> <data_dir>/<data_file>`
* Run the jar using
  - `hadoop jar <app>.jar <main_class> <data_dir>/<data_file> <output_dir>` or
  - `hadoop jar <app>.jar <main_class> hdfs:///user/hadoop/<data_dir>/<data_file> hdfs:///user/hadoop/<output_dir>` or
  - `hadoop jar <app>.jar <main_class> hdfs://namenode:8020/user/hadoop/<data_dir>/<data_file> hdfs://namenode:8020/user/hadoop/<output_dir>`
package org.frozenarc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.ByteBuffer;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {

        /*Configuration conf = new Configuration();
        conf.set("fs.default.name", "hdfs://localhost:9020");
        conf.set("fs.defaultFS", "hdfs://localhost:9020");
        conf.set("dfs.namenode.rpc-address", "localhost:9020");
        conf.set("dfs.replication", "1");
        FileSystem fs = DistributedFileSystem.get(conf);
        FSDataInputStream inputStream = fs.open(new Path("hdfs://localhost:9020/user/mpanchal/README"));
        byte[] data = getBytes(inputStream);
        System.out.println(new String(data));*/

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "rating count");
        job.setJarByClass(App.class);
        job.setMapperClass(MapClass.class);
        job.setReducerClass(ReduceClass.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

    /*public static byte[] getBytes(InputStream inputStream) throws IOException {
        byte[] data = new byte[0];

        int dataRead;
        try {
            do {
                byte[] chunkData = new byte[8192];
                dataRead = inputStream.read(chunkData);
                if (dataRead != -1) {
                    byte[] temp = data;
                    data = new byte[data.length + dataRead];
                    System.arraycopy(temp, 0, data, 0, temp.length);
                    System.arraycopy(chunkData, 0, data, temp.length, dataRead);
                }
            } while(dataRead != -1);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }

        }

        return data;
    }*/
}

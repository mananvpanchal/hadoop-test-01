package org.frozenarc;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Author: mpanchal
 * Date: 26-06-2024 17:41
 */
public class MapClass extends Mapper<Object, Text, Text, IntWritable> {

    @Override
    protected void map(Object key, Text value, Mapper<Object, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] values = line.split("\t");
        context.write(new Text(values[2].trim()), new IntWritable(1));
    }
}

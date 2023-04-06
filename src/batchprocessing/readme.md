UserReader extends the AbstractItemReader class, which has two key methods –
open() and readItem(). In our case, the first one opens the META-INF/user.txt file,
while the second one reads each line of the file.

After that, the UserProcessor class extends the ItemProcessor class, which has a
processItem() method. It gets the item read by readItem() (from UserReader) to
generate the User object that we want.

Once all the items have been processed and are available in a list (in memory), we use the
UserWriter class. This extends the AbstractItemWriter class and contains the
writeItems method. In our case, we use it to persist the data that we read from the
user.txt file.

Now, we just need to use UserBean to run the job:

    public void run() {
       try {
         JobOperator job = BatchRuntime.getJobOperator();
         long jobId = job.start("acess-user", new Properties());
         System.out.println("Job started: " + jobId);
       } catch (JobStartException ex) {
         System.out.println(ex.getMessage());
       }
    }

The job.start() method is referencing the acess-user.xml file, thereby enabling our
reader, processor, and writer to work together.
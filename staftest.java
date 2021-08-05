package com.company;
import com.ibm.staf.*;
import java.util.*;

public class staftest {
    public static void main(String argv[])
    {
        try
        {
            // Create a STAFHandle

            STAFHandle handle = new STAFHandle("MyApplication");
            System.out.println("My handle is: " + handle.getHandle());

            try
            {
                // Submit a synchronous request to the ECHO service on
                // the local machine

                STAFResult result = handle.submit2(
                        "local", "ECHO", "ECHO Hello");

                if (result.rc != 0)
                {
                    System.out.println(
                            "ERROR: STAF local ECHO ECHO Hello failed. RC: " +
                                    result.rc + ", Result: " + result.result);
                }
                else
                {
                    System.out.println("ECHO Result: " + result.result);
                }

                // Or submit an asynchronous request to the ECHO service on
                // the local machine

                result = handle.submit2(
                        STAFHandle.ReqRetain, "local", "ECHO", "ECHO Hello");

                if (result.rc != 0)
                {
                    System.out.println(
                            "ERROR: STAF local ECHO ECHO Hello failed. RC: " +
                                    result.rc + ", Result: " + result.result);
                }
                else
                {
                    System.out.println(
                            "Asynchronous ECHO Request number: " + result.result);
                }
            }
            finally
            {
                handle.unRegister();
            }
        }
        catch (STAFException e)
        {
            System.out.println(
                    "Error (un)registering with STAF, RC:" + e.rc);
            System.exit(1);
        }

    }   // End of main()

}

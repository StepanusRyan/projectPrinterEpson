package com.stepanusryan.epsontestprint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.epson.epos2.Epos2CallbackCode;
import com.epson.epos2.Epos2Exception;
import com.epson.epos2.printer.Printer;
import com.epson.epos2.printer.PrinterStatusInfo;
import com.epson.epos2.printer.ReceiveListener;
import com.epson.epos2.printer.StatusChangeListener;
import com.epson.eposprint.Builder;
import com.epson.eposprint.EposException;
import com.epson.eposprint.Print;

public class MainActivity extends AppCompatActivity implements ReceiveListener {
    Printer mPrinter = null;
    Button testPrint;

    Print printer = new Print();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testPrint = findViewById(R.id.btnTest);

        //Printer Selection
        try {
            mPrinter = new Printer(Printer.TM_U220,Printer.MODEL_ANK,MainActivity.this);
            mPrinter.setReceiveEventListener(this);

            mPrinter.addTextAlign(Printer.ALIGN_CENTER);
            mPrinter.addText("Test Print Epson Check");

            mPrinter.connect("TCP:192.168.103.202",Printer.PARAM_DEFAULT);


        } catch (Epos2Exception e) {
            if(e.getErrorStatus() == Epos2Exception.ERR_CONNECT)
            {
                Log.e("testing", "error connect");
            }
            if(e.getErrorStatus() == Epos2Exception.ERR_ALREADY_OPENED)
            {
                Log.e("testing", "already open");
            }
            if(e.getErrorStatus() == Epos2Exception.ERR_ALREADY_USED)
            {
                Log.e("testing", "already used");
            }
            if(e.getErrorStatus() == Epos2Exception.ERR_BOX_CLIENT_OVER)
            {
                Log.e("testing", "box client over");
            }
            if(e.getErrorStatus() == Epos2Exception.ERR_BOX_COUNT_OVER)
            {
                Log.e("testing", "count over");
            }
            if(e.getErrorStatus() == Epos2Exception.ERR_DISCONNECT)
            {
                Log.e("testing", "disconnect");
            }
            if(e.getErrorStatus() == Epos2Exception.ERR_FAILURE)
            {
                Log.e("testing", "failure");
            }
            if(e.getErrorStatus() == Epos2Exception.ERR_ILLEGAL)
            {
                Log.e("testing", "illegal");
            }
            if(e.getErrorStatus() == Epos2Exception.ERR_IN_USE)
            {
                Log.e("testing", "in use");
            }
            if(e.getErrorStatus() == Epos2Exception.ERR_MEMORY)
            {
                Log.e("testing", "memory");
            }
            Toast.makeText(MainActivity.this, ""+e.getErrorStatus() + " " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }



        //Creating print data
//        try {
//            mPrinter.addTextAlign(Printer.ALIGN_CENTER);
//            mPrinter.addText("Test Print Epson Check");
//        } catch (Epos2Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        //Sending print data
//        try {
//            mPrinter.connect("TCP:192.168.103.202",Printer.PARAM_DEFAULT);
//        } catch (Epos2Exception e) {
//            Log.d("MainActivity",""+e.getMessage());
//            if(e.getErrorStatus() == Epos2Exception.ERR_CONNECT)
//            {
//                Log.e("testing", "error connect");
//            }
//            if(e.getErrorStatus() == Epos2Exception.ERR_ALREADY_OPENED)
//            {
//                Log.e("testing", "already open");
//            }
//            if(e.getErrorStatus() == Epos2Exception.ERR_ALREADY_USED)
//            {
//                Log.e("testing", "already used");
//            }
//            if(e.getErrorStatus() == Epos2Exception.ERR_BOX_CLIENT_OVER)
//            {
//                Log.e("testing", "box client over");
//            }
//            if(e.getErrorStatus() == Epos2Exception.ERR_BOX_COUNT_OVER)
//            {
//                Log.e("testing", "count over");
//            }
//            if(e.getErrorStatus() == Epos2Exception.ERR_DISCONNECT)
//            {
//                Log.e("testing", "disconnect");
//            }
//            if(e.getErrorStatus() == Epos2Exception.ERR_FAILURE)
//            {
//                Log.e("testing", "failure");
//            }
//            if(e.getErrorStatus() == Epos2Exception.ERR_ILLEGAL)
//            {
//                Log.e("testing", "illegal");
//            }
//            if(e.getErrorStatus() == Epos2Exception.ERR_IN_USE)
//            {
//                Log.e("testing", "in use");
//            }
//            if(e.getErrorStatus() == Epos2Exception.ERR_MEMORY)
//            {
//                Log.e("testing", "memory");
//            }
////            throw new RuntimeException(e);
//        }
//
//        try {
//            mPrinter.sendData(Printer.PARAM_DEFAULT);
//        } catch (Epos2Exception e) {
////            throw new RuntimeException(e);
//        }


//        testPrint.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            }
//        });
    }
    public void onPtrReceive(final Printer printerObj, final int code, final PrinterStatusInfo status,
                             final String printJobId) {
        runOnUiThread(new Runnable() {
            @Override
            public synchronized void run() {
                if (code == Epos2CallbackCode.CODE_SUCCESS) {
                    //Displays successful print messages
                }
                else {
                    //Displays error messages
                }
            }
        });
        new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                //Abort process
            }
        }).start();
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
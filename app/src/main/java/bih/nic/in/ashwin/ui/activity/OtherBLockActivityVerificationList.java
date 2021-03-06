package bih.nic.in.ashwin.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import bih.nic.in.ashwin.R;
import bih.nic.in.ashwin.adaptor.AshaActivityAccpRjctAdapter;
import bih.nic.in.ashwin.adaptor.AshaOther_ActivityForwardAdapter;
import bih.nic.in.ashwin.database.DataBaseHelper;
import bih.nic.in.ashwin.entity.AshaWoker_Entity;
import bih.nic.in.ashwin.entity.AshaWorkEntity;
import bih.nic.in.ashwin.entity.Financial_Month;
import bih.nic.in.ashwin.entity.Financial_Year;
import bih.nic.in.ashwin.entity.HscList_Entity;
import bih.nic.in.ashwin.ui.home.HomeFragment;
import bih.nic.in.ashwin.utility.CommonPref;
import bih.nic.in.ashwin.web_services.WebServiceHelper;

public class OtherBLockActivityVerificationList extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String faciltator_id="",facilitator_nm="",asha_worker_id="",asha_worker_nm="",fyear_id="",month_id="",user_role="",svrid="";
    TextView tv_name,tv_year,tv_month,tv_role,tv_forward,tv_approve_other;
    Financial_Year fyear;
    Financial_Month fmonth;
    RecyclerView rv_data,rv_data_monthly;
    Button btn_finalize;
    ArrayList<AshaWorkEntity> ashawork;
    String version="";
    Spinner sp_worker_other,sp_hsc_list_other;
    ArrayList<AshaWoker_Entity> ashaworkerList = new ArrayList<AshaWoker_Entity>();
    ArrayList<HscList_Entity> hscList = new ArrayList<HscList_Entity>();
    DataBaseHelper dbhelper;
    LinearLayout ll_monthly,ll_daily,ll_dmf_tab,ll_hsc;
    String tabType = "F";
    String hscname="",hsccode="";
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_b_lock_verification_list);
        initialise();
        dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dbhelper=new DataBaseHelper(this);

        user_role=getIntent().getStringExtra("role");
        //   svrid=getIntent().getStringExtra("svr");
        fyear=(Financial_Year)getIntent().getSerializableExtra("fyear");
        fmonth=(Financial_Month)getIntent().getSerializableExtra("fmonth");

        tv_role.setText(CommonPref.getUserDetails(OtherBLockActivityVerificationList.this).getUserrole());
        tv_year.setText(fyear.getFinancial_year());
        tv_month.setText(fmonth.get_MonthName());

        new GetHScList().execute();

        tv_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabType = "F";
                handleTabView();
            }
        });


        tv_approve_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabType = "A";
                handleTabView();
            }
        });
    }

    public void initialise()
    {
        tv_name=findViewById(R.id.tv_name);
        tv_year=findViewById(R.id.tv_year);
        tv_month=findViewById(R.id.tv_month);
        tv_role=findViewById(R.id.tv_role);
        rv_data = findViewById(R.id.recyclerview_data_other);
        rv_data_monthly = findViewById(R.id.recyclerview_data_monthly);
        btn_finalize = findViewById(R.id.btn_finalize);
        sp_worker_other = findViewById(R.id.sp_asha_other);
        sp_hsc_list_other = findViewById(R.id.sp_hsc_list_other);
        ll_monthly = findViewById(R.id.ll_monthly);
        ll_daily = findViewById(R.id.ll_daily);
        ll_dmf_tab = findViewById(R.id.ll_dmf_tab);

        tv_forward = findViewById(R.id.tv_forward);
        tv_approve_other = findViewById(R.id.tv_approve_other);
        ll_hsc = findViewById(R.id.ll_hsc);
        ll_daily.setVisibility(View.GONE);
        ll_monthly.setVisibility(View.GONE);
        //ll_hsc.setVisibility(View.GONE);

        // btn_finalize.setVisibility(View.GONE);
        sp_worker_other.setOnItemSelectedListener(this);
        sp_hsc_list_other.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        switch (parent.getId())
        {
            case R.id.sp_hsc_list_other:
                if (position > 0)
                {
                    HscList_Entity role = hscList.get(position - 1);
                    hscname = role.get_HSCName_Hn();
                    hsccode = role.get_HSCCode();
                    new GetAshaWorkersList().execute();
                }
                break;
            case R.id.sp_asha_other:
                if (position > 0)
                {
                    AshaWoker_Entity role = ashaworkerList.get(position - 1);
                    asha_worker_nm = role.get_Asha_Name_Hn();
                    asha_worker_id = role.get_ASHAID();
                    svrid = role.get_svr_id();
                    tv_name.setText(asha_worker_nm);
                    ll_dmf_tab.setVisibility(View.VISIBLE);
                    new SynchronizeAshaActivityList().execute();
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

    }

    public void handleTabView()
    {
        switch (tabType)
        {
            case "F":
                tv_forward.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv_approve_other.setTextColor(getResources().getColor(R.color.colorGreyDark));
                rv_data.setVisibility(View.VISIBLE);
                rv_data_monthly.setVisibility(View.GONE);
//                if (CommonPref.getUserDetails(AshaWorker_Facilitator_Activity_List.this).getUserrole().equals("HSC"))
//                {
                new SynchronizeAshaActivityList().execute();
                // }

                break;

            case "A":
                tv_forward.setTextColor(getResources().getColor(R.color.colorGreyDark));
                tv_approve_other.setTextColor(getResources().getColor(R.color.colorPrimary));
                rv_data_monthly.setVisibility(View.VISIBLE);
                rv_data.setVisibility(View.GONE);
                new ApproveOtherActivityList().execute();
                break;
        }
    }

    private class GetHScList extends AsyncTask<String, Void, ArrayList<HscList_Entity>>
    {

        @Override
        protected void onPreExecute()
        {
            dialog.setMessage("Loading Hsc details...");
            dialog.show();
        }

        @Override
        protected ArrayList<HscList_Entity> doInBackground(String... param)
        {
            return WebServiceHelper.getHscList_Other(CommonPref.getUserDetails(getApplicationContext()).getBlockCode());
        }

        @Override
        protected void onPostExecute(ArrayList<HscList_Entity> result)
        {
            if(dialog.isShowing())
                dialog.dismiss();

            if (result.size()>0)
            {
                Log.d("Resultgfg", "" + result);
                hscList=result;
                loadHscList();
                DataBaseHelper helper = new DataBaseHelper(getApplicationContext());

            }
            else
            {
                Toast.makeText(getApplicationContext(), "No HSC List Found For Other Block Entry", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void loadHscList()
    {
        ArrayList array = new ArrayList<String>();
        array.add("-Select-");

        for (HscList_Entity info: hscList)
        {
            array.add(info.get_HSCName_Hn());
        }

        ArrayAdapter adaptor = new ArrayAdapter(OtherBLockActivityVerificationList.this, android.R.layout.simple_spinner_item, array);
        adaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_hsc_list_other.setAdapter(adaptor);
    }

    private class GetAshaWorkersList extends AsyncTask<String, Void, ArrayList<AshaWoker_Entity>>
    {
        @Override
        protected void onPreExecute()
        {
            dialog.setMessage("Loading Asha details...");
            dialog.show();
        }

        @Override
        protected ArrayList<AshaWoker_Entity> doInBackground(String... param)
        {
            String hsc_code="";
            if (CommonPref.getUserDetails(getApplicationContext()).getUserrole().equals("BLKBCM"))
            {
                hsc_code=hsccode;

            }
            else
            {
                hsc_code=CommonPref.getUserDetails(getApplicationContext()).getHSCCode();
            }
            Log.d("vbbjvjnjn"+CommonPref.getUserDetails(getApplicationContext()).getDistrictCode()+CommonPref.getUserDetails(getApplicationContext()).getBlockCode()+hsc_code,fyear.getYear_Id()+fmonth.get_MonthId());

            return WebServiceHelper.getAshaWorkerList_Other(CommonPref.getUserDetails(getApplicationContext()).getDistrictCode(),CommonPref.getUserDetails(getApplicationContext()).getBlockCode(),hsc_code,fyear.getYear_Id(),fmonth.get_MonthId());
        }

        @Override
        protected void onPostExecute(ArrayList<AshaWoker_Entity> result) {
            if(dialog.isShowing())
                dialog.dismiss();

            if (result != null && result.size()>0)
            {
                Log.d("Resultgfg", "" + result);

                DataBaseHelper helper = new DataBaseHelper(getApplicationContext());

                ashaworkerList = result;
                loadWorkerFascilatorData();

                Toast.makeText(getApplicationContext(), "Asha worker list loaded", Toast.LENGTH_SHORT).show();

            }
            else {
                loadWorkerFascilatorData();
                Toast.makeText(getApplicationContext(), "No Asha Worker List In This Hsc", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void loadWorkerFascilatorData()
    {

        ArrayList array = new ArrayList<String>();
        array.add("-Select-");

        for (AshaWoker_Entity info: ashaworkerList)
        {
            array.add(info.get_ASHAID()+":-"+info.get_Asha_Name_Hn());
        }

        ArrayAdapter adaptor = new ArrayAdapter(OtherBLockActivityVerificationList.this, android.R.layout.simple_spinner_item, array);
        adaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_worker_other.setAdapter(adaptor);

    }

    private class SynchronizeAshaActivityList extends AsyncTask<String, Void, ArrayList<AshaWorkEntity>> {

        private final ProgressDialog dialog = new ProgressDialog(OtherBLockActivityVerificationList.this);

        private final AlertDialog alertDialog = new AlertDialog.Builder(OtherBLockActivityVerificationList.this).create();

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading details...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<AshaWorkEntity> doInBackground(String... param)
        {
            // return WebServiceHelper.getAshaWorkActivityList(svrid,fmonth.get_MonthId(),fyear.getYear_Id(),CommonPref.getUserDetails(AshaWorker_Facilitator_Activity_List.this).getUserrole());
            return WebServiceHelper.getAshaWork_Other_ActivityList(svrid,fmonth.get_MonthId(),fyear.getYear_Id(),CommonPref.getUserDetails(OtherBLockActivityVerificationList.this).getUserrole(),CommonPref.getUserDetails(OtherBLockActivityVerificationList.this).getBlockCode());
        }

        @Override
        protected void onPostExecute(ArrayList<AshaWorkEntity> result)
        {
            if (this.dialog.isShowing())
            {
                this.dialog.dismiss();
            }

            if (result != null)
            {
                ashawork=result;
                setupRecuyclerView(result);
            }
        }
    }

    public void setupRecuyclerView(ArrayList<AshaWorkEntity> data)
    {
        ll_daily.setVisibility(View.VISIBLE);
        rv_data.setVisibility(View.VISIBLE);
        ll_monthly.setVisibility(View.GONE);
        rv_data_monthly.setVisibility(View.GONE);
        rv_data.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        AshaOther_ActivityForwardAdapter adapter = new AshaOther_ActivityForwardAdapter(OtherBLockActivityVerificationList.this, data, fyear, fmonth);
        rv_data.setAdapter(adapter);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
//        Intent i=new Intent(OtherBLockActivityVerificationList.this, UserHomeActivity.class);
//        startActivity(i);
//        finish();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        handleTabView();
    }

    private class ApproveOtherActivityList extends AsyncTask<String, Void, ArrayList<AshaWorkEntity>> {

        private final ProgressDialog dialog = new ProgressDialog(OtherBLockActivityVerificationList.this);

        private final AlertDialog alertDialog = new AlertDialog.Builder(OtherBLockActivityVerificationList.this).create();

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading details...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<AshaWorkEntity> doInBackground(String... param)
        {            // return WebServiceHelper.getAshaWorkActivityList(svrid,fmonth.get_MonthId(),fyear.getYear_Id(),CommonPref.getUserDetails(AshaWorker_Facilitator_Activity_List.this).getUserrole());
            return WebServiceHelper.getAshaWorkOtherActivityListForApproval(svrid,fmonth.get_MonthId(),fyear.getYear_Id(),hsccode,CommonPref.getUserDetails(OtherBLockActivityVerificationList.this).getUserrole(),CommonPref.getUserDetails(OtherBLockActivityVerificationList.this).getBlockCode());
        }

        @Override
        protected void onPostExecute(ArrayList<AshaWorkEntity> result) {
            if (this.dialog.isShowing())
            {
                this.dialog.dismiss();
            }

            if (result != null)
            {
                ashawork=result;
                setupRecuyclerViewAccpRjctOther(result);

                //  new SynchronizeMonthlyAshaActivityList().execute();

            }
        }
    }


    public void setupRecuyclerViewAccpRjctOther(ArrayList<AshaWorkEntity> data)
    {
        ll_monthly.setVisibility(View.VISIBLE);
        rv_data_monthly.setVisibility(View.VISIBLE);
        ll_daily.setVisibility(View.GONE);
        rv_data.setVisibility(View.GONE);
        rv_data_monthly.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        AshaActivityAccpRjctAdapter adapter = new AshaActivityAccpRjctAdapter(OtherBLockActivityVerificationList.this, data, fyear, fmonth);
        rv_data_monthly.setAdapter(adapter);
    }
}

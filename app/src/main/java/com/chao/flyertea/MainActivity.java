package com.chao.flyertea;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.chao.flyertea.bean.Bank;
import com.chao.flyertea.bean.CreditVariable;
import com.chao.flyertea.bean.Favor;
import com.chao.flyertea.bean.FavorVariable;
import com.chao.flyertea.bean.ForumThread;
import com.chao.flyertea.bean.ForumVariable;
import com.chao.flyertea.bean.LoginVariable;
import com.chao.flyertea.bean.ReplyResult;
import com.chao.flyertea.bean.Result;
import com.chao.flyertea.bean.ThreadDetail;
import com.chao.flyertea.bean.ThreadPost;
import com.chao.flyertea.bean.ThreadVariable;
import com.chao.flyertea.net.FlyerteaApi;
import com.chao.flyertea.util.Constants;
import com.chao.flyertea.util.RequestUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "XUCHAO";

    private int replyCount = 0;

//    private int forumId = 59;

    public TextView successInfo, errorInfo;

    private Button creditBtn;

    private ListView bankList;

    private MyAdapter myAdapter;


    private MyHandler myHandler;

    private SharedPreferences sp;

    private ArrayList<Bank> favorList;

    private RadioGroup radiogroup;

    private RadioButton lastReply, lastPost;
    private LoginVariable login = null;
    private RadioGroup typeIdGroup;
    private RadioButton rb2, rb3, rb4, rb6, rb7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (LoginVariable) getIntent().getSerializableExtra("login");
        myHandler = new MyHandler(this);
        initViews();
        if (login != null) {
            Message msg = myHandler.obtainMessage();
            msg.what = 1;
            myHandler.sendMessage(msg);
            getMyFavourite();
        }
    }


    private void initViews() {
        bankList = findViewById(R.id.list);
        creditBtn = findViewById(R.id.credit);
        creditBtn.setOnClickListener(this);
        radiogroup = findViewById(R.id.radiogroup);
        lastPost = findViewById(R.id.last_post);
        lastReply = findViewById(R.id.last_reply);
        typeIdGroup = findViewById(R.id.typeGroup);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb6 = findViewById(R.id.rb6);
        rb7 = findViewById(R.id.rb7);
        myAdapter = new MyAdapter(this, new ArrayList<Bank>());
        bankList.setAdapter(myAdapter);
        bankList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (favorList != null && favorList.size() > 0) {
                    reset();
                    Bank bank = favorList.get(position);
                    getList(bank.getFid());
                }
            }
        });
        successInfo = findViewById(R.id.successInfo);
        errorInfo = findViewById(R.id.errorInfo);
        sp = getSharedPreferences("flyertea", Context.MODE_PRIVATE);
        long time = sp.getLong("lastRequest", 0);
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss", Locale.CHINA);
        errorInfo.setText("最后请求时间" + sdf.format(new Date(time)));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.credit) {
            for (int i = 1; i < 15; i++) {
                getCredit(String.valueOf(i));
            }
        }

    }

    private void reset() {
        if (login == null) {
            successInfo.setText("需要重新登录");
            errorInfo.setText("需要重新登录");
        } else {
            replyCount = 0;
            successInfo.setText("登录成功！");
            long time = sp.getLong("lastRequest", 0);
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss", Locale.CHINA);
            errorInfo.setText("最后请求时间" + sdf.format(new Date(time)));
        }
    }


    private void getCredit(String taskid) {
        HashMap<String, String> params = RequestUtils.createRequestParams();
        params.put("version", "6");
        params.put("module", "users");
        params.put("type", "apptask");
        params.put("method", "get");
        params.put("taskid", taskid);

        final FlyerteaApi request = RequestUtils.createRequest(params);
        request.getCredit().enqueue(new Callback<Result<CreditVariable, Object>>() {
            @Override
            public void onResponse(Call<Result<CreditVariable, Object>> call, Response<Result<CreditVariable, Object>> response) {
                if (response.isSuccessful()) {
                    Result<CreditVariable, Object> result = response.body();
                    CreditVariable variable = result.getVariables();
                }
            }

            @Override
            public void onFailure(Call<Result<CreditVariable, Object>> call, Throwable t) {

            }
        });


    }

    private void getMyFavourite() {
        HashMap<String, String> params = RequestUtils.createRequestParams();
        params.put("module", "basicdata");
        params.put("type", "forumlist");
        params.put("version", "5");

        final FlyerteaApi request = RequestUtils.createRequest(params);
        request.getMyFavourite().enqueue(new Callback<Result<FavorVariable, Object>>() {
            @Override
            public void onResponse(Call<Result<FavorVariable, Object>> call, Response<Result<FavorVariable, Object>> response) {
                if (response.isSuccessful()) {
                    Result<FavorVariable, Object> result = response.body();
                    FavorVariable variable = result.getVariables();
                    ArrayList<Favor> favors = variable.getData();
                    if (favors != null && favors.size() > 0) {
                        Favor mFavor = favors.get(0);
                        favorList = mFavor.getTypes();
                        myAdapter.setList(favorList);
                    }
                }
            }

            @Override
            public void onFailure(Call<Result<FavorVariable, Object>> call, Throwable t) {

            }
        });
    }


    private void getList(String fid) {
        HashMap<String, String> params = RequestUtils.createRequestParams();

        if (lastPost.isChecked())
            params.put("orderby", "lastpost");
        else
            params.put("orderby", "dateline");
        params.put("page", "1");
        params.put("fid", fid);
        params.put("version", "4");
        params.put("filter", "typeid");
        params.put("module", Constants.MODULE_FORUM);
        int typeId = 0;
        if (rb2.isChecked()) {
            typeId = 2;
        } else if (rb3.isChecked()) {
            typeId = 3;
        } else if (rb4.isChecked()) {
            typeId = 4;
        } else if (rb6.isChecked()) {
            typeId = 6;
        } else if (rb7.isChecked()) {
            typeId = 7;
        }
        params.put("typeid", String.valueOf(typeId));

        FlyerteaApi request = RequestUtils.createRequest(params);
        request.getForumListByFid().enqueue(new Callback<Result<ForumVariable, Object>>() {
            @Override
            public void onResponse(Call<Result<ForumVariable, Object>> call, Response<Result<ForumVariable, Object>> response) {
                if (response.isSuccessful()) {
                    Result<ForumVariable, Object> result = response.body();
                    // 这里拿到的是工行板块的帖子列表
                    List<ForumThread> list = result.getVariables().getForum_threadlist();
                    int size = new Random().nextInt(Math.min(list.size(), 20));
                    for (int i = 0; i < size; i++) {
                        ForumThread thread = list.get(i);
                        getResponseByTid(thread.getTid());
                    }
                }
            }

            @Override
            public void onFailure(Call<Result<ForumVariable, Object>> call, Throwable t) {
                Log.e(TAG, "--------------------------");
            }
        });


    }


    private void getResponseByTid(String tid) {
        HashMap<String, String> params = RequestUtils.createRequestParams();
        params.put("dateline", "asc");
        params.put("version", "5");
        params.put("module", Constants.MODULE_THREAD);
        params.put("page", "1");
        params.put("ppp", "200");
        params.put("filter", "typeid");
        params.put("tid", tid);
        FlyerteaApi request = RequestUtils.createRequest(params);
        request.getResponseListByTid().enqueue(new Callback<Result<ThreadVariable, Object>>() {
            @Override
            public void onResponse(Call<Result<ThreadVariable, Object>> call, Response<Result<ThreadVariable, Object>> response) {

                Result<ThreadVariable, Object> pResult = response.body();
                String formhash = pResult.getVariables().getFormhash();
                ThreadDetail detail = pResult.getVariables().getData().getThreaddetail();
                List<ThreadPost> list = pResult.getVariables().getData().getPosts();
                String postMsg = genericPostMsg2(detail, list);
                Log.e(TAG, postMsg);
                if (!postMsg.equals("breakX"))
                    postThreadBySmart(postMsg, formhash, detail.getFid(), detail.getTid(), detail.getAuthorid());
            }

            @Override
            public void onFailure(Call<Result<ThreadVariable, Object>> call, Throwable t) {

            }
        });
    }


    /**
     * 智能回帖
     *
     * @param postMsg  回帖的内容
     * @param formhash 上一次请求回来的formhash
     * @param tid      帖子id
     * @param authorId 发帖人id
     */
    private void postThreadBySmart(String postMsg, String formhash, String fid, String tid, String authorId) {
        HashMap<String, String> query = RequestUtils.createRequestParams();
        query.put("module", Constants.MODULE_POST);
        query.put("mobile", "yes");
        query.put("appcan", "appcan");
        query.put("fid", fid);
        query.put("tid", tid);
        query.put("version", "5");
        query.put("replysubmit", "yes");
        query.put("formhash", formhash);
        // 发起这个帖子的人的Id
        query.put("replyouid", authorId);

        HashMap<String, String> msg = new HashMap<>();
        msg.put("message", postMsg);

        FlyerteaApi request = RequestUtils.createRequest(query);

        request.postThreadBySmart(msg).enqueue(new Callback<ReplyResult>() {
            @Override
            public void onResponse(Call<ReplyResult> call, Response<ReplyResult> response) {
                ReplyResult result = response.body();
                if (result.getMessage().getMessageval().equals(Constants.POST_SUCCESS)) {
                    //发帖成功
                    replyCount++;
                    Message msg = myHandler.obtainMessage();
                    msg.what = 2;
                    msg.obj = replyCount;
                    myHandler.sendMessage(msg);
                } else {
                    Message msg = myHandler.obtainMessage();
                    msg.what = 3;
                    msg.obj = result.getMessage().getMessagestr();
                    myHandler.sendMessage(msg);
                }
            }

            @Override
            public void onFailure(Call<ReplyResult> call, Throwable t) {
                Log.e(TAG, "-----------------------");
            }
        });
    }


    private String genericPostMsg2(ThreadDetail detail, List<ThreadPost> list) {
        String postMsg = "感谢分享";
        for (ThreadPost post : list) {
            if (post.getAuthor().equals(login.getMember_username()) && post.getMessage().equals("感谢分享")) {
                postMsg = "breakX";
            }
        }
        return postMsg;
    }

    /**
     * 智能生成回复内容
     *
     * @return
     */
    private String genericPostMsg(ThreadDetail detail, List<ThreadPost> list) {
        // 默认水贴 羡慕
        String postMsg = Constants.POST_DEFAULT_MSG;
        if (list != null && list.size() > 1) {
            int size = list.size();
            if (!list.get(size - 1).getAuthor().equals("飞翔的荷兰号")) {
                // 检查大佬们的回复
                postMsg = getGodsReply(list);
                // 没有大佬的权威回复则随机选取
                if (postMsg.equals("")) {
                    int max = list.size() - 1;
                    int index = new Random(100).nextInt(max);
                    if (index < 0) {
                        postMsg = Constants.POST_DEFAULT_MSG;
                    } else {
                        postMsg = list.get(index).getMessage();
                    }
                }
            } else {
                // 最后一个回帖人是自己就略过
                postMsg = "breakX";
            }
        } else {
            if (detail.getSubforumname() != null) {
                if (detail.getSubforumname().equals(Constants.FORUM_TYPE_HELP)) {
                    postMsg = "帮顶，关注下";
                } else if (detail.getSubforumname().equals(Constants.FORUM_TYPE_USE_CARD) ||
                        detail.getSubforumname().equals(Constants.FORUM_TYPE_EXP) ||
                        detail.getSubforumname().equals(Constants.FORUM_TYPE_ACTIVITY)) {
                    postMsg = "感谢分享";
                } else if (detail.getSubforumname().equals(Constants.FORUM_TYPE_CHAT)) {
                    postMsg = "emmmmmmmm………………";
                } else if (detail.getSubforumname().equals(Constants.FORUM_TYPE_COMPLAIN)) {
                    postMsg = "吃瓜看戏";
                }
            } else {
                postMsg = "breakX";
            }
        }
        return stripHtml(postMsg);
    }


    private String getGodsReply(List<ThreadPost> list) {
        // 这里可以保证list不为空，长度大于1
        String result = "";
        label:
        for (ThreadPost post : list) {
            String tid = post.getTid();
            String authorId = post.getAuthorid();
            switch (tid) {
                case "333":
                    //icbc
                    if (authorId.equals("1757357") || authorId.equals("1828649")) {
                        result = post.getMessage();
                        break label;
                    }
                    break;
                case "340":
                    //abc
                    if (authorId.equals("21353278") || authorId.equals("1651500") || authorId.equals("2560850")) {
                        result = post.getMessage();
                        break label;
                    }
                    break;
                case "321":
                    // boc
                    if (authorId.equals("1807947") || authorId.equals("1658974") || authorId.equals("2583677")) {
                        result = post.getMessage();
                        break label;
                    }
                    break;
                case "319":
                    // cmb
                    if (authorId.equals("1994966") || authorId.equals("2602440") || authorId.equals("1833864")) {
                        result = post.getMessage();
                        break label;
                    }
                    break;
                case "339":
                    // ccb
                    if (authorId.equals("2560850") || authorId.equals("2651160") || authorId.equals("103723")) {
                        result = post.getMessage();
                        break label;
                    }
                    break;
            }
        }
        return result;
    }


    private String stripHtml(String content) {
        // <p>段落替换为换行
        content = content.replaceAll("<p .*?>", "\r\n");
        // <br><br/>替换为换行
        content = content.replaceAll("<br\\s*/?>", "\r\n");
        // 去掉其它的<>之间的东西
        content = content.replaceAll("\\<.*?>", "");

        content = content.replaceAll("\\[.*?]", "");
        // 去掉空格
//        content = content.replaceAll(" ", "");
        return content;
    }
}


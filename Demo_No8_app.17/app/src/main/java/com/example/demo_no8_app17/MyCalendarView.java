package com.example.demo_no8_app17;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;


/**
 * Created by chaojie on 2015/9/28.
 */
public class MyCalendarView extends LinearLayout implements View.OnTouchListener, View.OnClickListener {

    /**顶部年月显示**/
    private TextView textViewTop;
    private Context mContext;

    /**整个布局添加的主布局**/
    private LinearLayout linearLayoutMain;
    /**中间显示日期布局**/
    private LinearLayout linearLayoutChild;

    /**日期布局存储列表**/
    private List<ViewHolder> viewHolderList = new ArrayList<>();

    private float motionEventDownX;

    /**当前显示的月份**/
    private int currentMonth;
    /**当前显示的年份**/
    private int currentYear;



    /**点击日期监听接口**/
    private ClickDateListener clickDateListener;
    /**今天时间背景**/
    private Drawable drawableCurrentDayBg;
    /**平常日期背景**/
    private Drawable drawableNormal;
    /**点击日期背景**/
    private Drawable drawableClickBg;
    /***上次点击的view**/
    private View lastClickView;
    /**画笔**/
    private Canvas canvas;
    private Bitmap bitmap;
    private Paint paint;

    private final int MARGIN_TOP = 20;
    private final int MARGIN_BOTTOM = 20;
    private final int MARGIN_LEFT = 30;
    private final int MARGIN_RIGHT = 30;

    private final int MARGIN_LITTLE = 5;

    private final int TEXT_TOP_SIZE = 18;
    private final int TEXT_DAY = 16;

    private final int ONE_WEEK = 7;
    private final int WEEKS = 6;

    private final float INTERVAL_X = 30;

    private final int TEXTVIEW_HEIGHT = 60;

    /***设置选中的日期年份**/
    private int selectYear = 0;
    /***设置选中的日期月份**/
    private int selectMOnth = 0;
    /***设置选中的日期天**/
    private int selectDay = 0;

    private String CHUXI = "除夕";
    private String SPRING = "春节";
    private String VACATION = "休";

    /**假期日期**/
    private HashMap<Long, Long> vacationList = new HashMap<>();

    private final String LOG = MyCalendarView.class.getName();

    public MyCalendarView(Context context) {
        super(context);
        mContext = context;
        init(context);
    }

    public MyCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(context);
    }

    public MyCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(context);
    }



//    canvas画笔
    private void initCanvas() {
        bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.rgb(50, 192, 196));
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        canvas.drawCircle(50, 50, 50, paint);
        drawableCurrentDayBg = new BitmapDrawable(bitmap);

        Bitmap bitmapNormal = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        drawableNormal = new BitmapDrawable(bitmapNormal);

        Bitmap bitmapClick = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.rgb(46, 155, 0));
        paint.setStrokeWidth(10);
        canvas.setBitmap(bitmapClick);
        canvas.drawCircle(50, 50, 45, paint);
        drawableClickBg = new BitmapDrawable(bitmapClick);
    }

    private void init(Context context) {
//        calendarUtil = new CalendarUtil();

        /**设置主布局样式 start**/
        linearLayoutMain = new LinearLayout(mContext);
        LayoutParams layoutParamsMian = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        linearLayoutMain.setLayoutParams(layoutParamsMian);
        linearLayoutMain.setOrientation(VERTICAL);
        addView((linearLayoutMain));
        /**设置主布局样式 end**/

        Date date = new Date();

        /**设置顶部显示年月样式 start**/
        textViewTop = new TextView(context);
        textViewTop.setText(getTopTitle(date));
        textViewTop.setTextColor(getResources().getColor(android.R.color.black));
        LayoutParams layoutParamsTop = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        layoutParamsTop.setMargins(0, MARGIN_TOP, 0, 0);
        textViewTop.setGravity(Gravity.CENTER);
        textViewTop.setTextSize(TEXT_TOP_SIZE);
        textViewTop.setLayoutParams(layoutParamsTop);
        linearLayoutMain.addView(textViewTop);//将顶部显示的年月textview添加到主布局
        /**设置顶部显示年月样式 end**/

        /***设置当天日期样式 start**/
        LinearLayout linearLayout = new LinearLayout(mContext);
        LayoutParams layoutParamsCurrentDate = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, TEXTVIEW_HEIGHT + 20);
        linearLayout.setLayoutParams(layoutParamsCurrentDate);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayoutMain.addView(linearLayout);

        String currentDateText = "今天:" + (date.getYear() + 1900) + "年" + (date.getMonth() + 1) + "月" + date.getDate() + "日";
        TextView textViewCurrentDate = new TextView(mContext);
        LayoutParams layoutParamsCurrentTextViewDate = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsCurrentTextViewDate.setMargins(5, 5, 5, 5);
        textViewCurrentDate.setLayoutParams(layoutParamsCurrentTextViewDate);
        textViewCurrentDate.setText(currentDateText);
        textViewCurrentDate.setTextSize(14);
        textViewCurrentDate.setGravity(Gravity.CENTER);
        linearLayout.addView(textViewCurrentDate);

        linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date1 = new Date();
                initCalendarDays(date1.getYear(), date1.getMonth());
            }
        });
        /***设置当天日期样式 end**/

        /**设置顶部年月下面的横线样式 start**/
        TextView textViewa = new TextView(context);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, 1);
        layoutParams.setMargins(0, MARGIN_TOP, 0, 0);
        textViewa.setLayoutParams(layoutParams);
        textViewa.setBackgroundColor(Color.LTGRAY);
        linearLayoutMain.addView(textViewa);//将横线添加到主布局
        /**设置顶部年月下面的横线样式 end**/

        /**设置显示星期栏样式 start ----------------------**/
        /**设置显示星期布局样式 start**/
        LinearLayout linearLayoutWeek = new LinearLayout(mContext);
        LayoutParams layoutParamsWeek = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        linearLayoutWeek.setLayoutParams(layoutParamsWeek);
        linearLayoutWeek.setOrientation(HORIZONTAL);
        /**设置显示星期布局样式 end**/

        /**设置星期样式 start**/
        for (int i = 0; i < ONE_WEEK; ++i) {
            /**设置星期布局样式 start**/
            LinearLayout linearLayoutChild = new LinearLayout(mContext);
            LayoutParams layoutParamsChild = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            layoutParamsChild.weight = 1;
            linearLayoutChild.setOrientation(HORIZONTAL);
            //layoutParamsChild.setMargins(MARGIN_LEFT, 0, 0, 0);
            linearLayoutChild.setLayoutParams(layoutParamsChild);
            /**设置星期布局样式 end**/

            /**设置显示星期的textView样式 start**/
            TextView textView = new TextView(context);
            LayoutParams layoutParams2 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            layoutParams2.gravity = Gravity.CENTER;
            textView.setText(getWeekDay(i));
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(layoutParams2);
            /**设置显示星期的textView样式 end**/

            linearLayoutChild.addView(textView);//将显示星期的textview添加到相应的星期布局中
            linearLayoutWeek.addView(linearLayoutChild);//将星期布局添加到星期布局栏中
        }
        /**设置星期样式 start**/
        linearLayoutMain.addView(linearLayoutWeek);//将显示星期的布局添加到主布局中
        /**设置显示星期栏样式 end*---------------------------*/

        /**设置星期下面的横线样式 start**/
        TextView textView = new TextView(context);
        layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, 1);
        layoutParams.setMargins(0, MARGIN_TOP, 0, 0);
        textView.setLayoutParams(layoutParams);
        textView.setBackgroundColor(Color.LTGRAY);
        linearLayoutMain.addView(textView);
        /**设置星期下面的横线样式 end**/

        /**设置显示日期的主布局样式 start**/
        linearLayoutChild = new LinearLayout(mContext);
        LayoutParams layoutParamsChild = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        linearLayoutChild.setOrientation(VERTICAL);
        linearLayoutChild.setLayoutParams(layoutParamsChild);
        linearLayoutMain.addView(linearLayoutChild);
        /**设置显示日期的布局样式 end**/

        initCanvas();//初始化画板画笔

        /**设置日期布局样式 start**/
        for (int i = 0; i < WEEKS; ++i) {
            /**设置一周日期栏主句样式 start**/
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.linearLayoutMain = new LinearLayout(mContext);
            LayoutParams layoutParams2 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

            viewHolder.linearLayoutMain.setOrientation(HORIZONTAL);
            viewHolder.linearLayoutMain.setLayoutParams(layoutParams2);
            viewHolder.viewHolderChildList = new ArrayList<>();

            linearLayoutChild.addView(viewHolder.linearLayoutMain);
            /**设置一周日期栏主句样式 end**/

            for (int j = 0; j < ONE_WEEK; ++j) {
                /**设置一个日期布局样式 start**/
                LayoutParams layoutParams3 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                layoutParams3.weight = 1;
                //layoutParams3.setMargins(MARGIN_LEFT - 10, 0, 0, 0);

                ViewHolderChild viewHolderChild = new ViewHolderChild();
                viewHolderChild.linearLayout = new LinearLayout(mContext);
                viewHolderChild.linearLayout.setOrientation(VERTICAL);
                viewHolderChild.linearLayout.setLayoutParams(layoutParams3);
                viewHolderChild.linearLayout.setGravity(Gravity.CENTER);
                /**设置一个日期布局样式 end**/

                /**设置显示日期的textview start**/
                LayoutParams layoutParamsText = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                layoutParamsText.setMargins(0, 5, 0, 0);
                //layoutParamsText.weight = 9;

                viewHolderChild.textViewDay = new TextView(mContext);
                viewHolderChild.textViewDay.setText("");
                viewHolderChild.textViewDay.setTextSize(TEXT_DAY);
                viewHolderChild.textViewDay.setTextColor(getResources().getColor(android.R.color.black));
                viewHolderChild.textViewDay.setGravity(Gravity.CENTER);
                viewHolderChild.textViewDay.setLayoutParams(layoutParamsText);

                layoutParamsText = new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParamsText.setMargins(0, 10, 0, 0);
                //layoutParamsText.weight = 5;
                viewHolderChild.textViewLunar = new TextView(mContext);
                viewHolderChild.textViewLunar.setText("");
                viewHolderChild.textViewLunar.setTextSize(TEXT_DAY - 8);
                viewHolderChild.textViewLunar.setGravity(Gravity.CENTER);
                viewHolderChild.textViewLunar.setLayoutParams(layoutParamsText);
                /**设置显示日期的textview end**/

                viewHolderChild.linearLayout.addView(viewHolderChild.textViewDay);//将显示日期的textview添加到日期布局中
                viewHolderChild.linearLayout.addView(viewHolderChild.textViewLunar);//将显示农历日期的textview添加到日期布局中

                viewHolder.linearLayoutMain.addView(viewHolderChild.linearLayout);//将日期布局添加到一周日期栏布局中
                viewHolder.viewHolderChildList.add(viewHolderChild);//将一周日期栏布局添加到日期主布局中

                viewHolderChild.linearLayout.setId(i * ONE_WEEK + (j + 1));
                viewHolderChild.linearLayout.setOnClickListener(this);//设置点击日期监听事件
                viewHolderChild.linearLayout.setOnTouchListener(this);
                viewHolder.linearLayoutMain.setOnTouchListener(this);
            }

            /**设置添加一条一周日期栏底部横线样式 start**/
            TextView textView1 = new TextView(context);
            layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, 1);
            layoutParams.setMargins(0, MARGIN_TOP, 0, 0);

            textView1.setLayoutParams(layoutParams);
            textView1.setBackgroundColor(Color.LTGRAY);
            linearLayoutChild.addView(textView1);
            viewHolderList.add(viewHolder);
            /**设置添加一条一周日期栏底部横线样式 end**/
        }
        /**设置日期布局样式 start**/

        linearLayoutChild.setOnTouchListener(this);//设置监听显示日期主布局触摸事件
        initCalendarDays(date.getYear(), date.getMonth());//初始化显示当月的数据
    }

    private String getTopTitle(Date date) {
        String topTitle = (date.getYear() + 1900) + "年" + (date.getMonth() + 1) + "月";
        return topTitle;
    }

    /**
     * 根据index获取星期几
     * @param index
     * @return
     */
    private String getWeekDay(int index) {
        switch (index) {
            case 0:
                return "日";
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 4:
                return "四";
            case 5:
                return "五";
            case 6:
                return "六";
        }
        return "";
    }

    /**
     * 根据时间获取一个月有多少天
     * @param date
     * @return
     */
    private int getMonthDays(Date date) {
        int days = 0;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return days;
    }

    /**
     * 初始化显示月的时间
     * @param year
     * @param month
     */
    public void initCalendarDays(int year, int month) {
        clear();
        Date todayDate = new Date();
        int today = todayDate.getDate();//当天的日期

        Date date = new Date();
        date.setTime(0);
        date.setYear(year);
        date.setMonth(month);
        date.setDate(1);

        String topTitle = getTopTitle(date);
        textViewTop.setText(topTitle);//显示当前的年月到顶部标题

        int days = getMonthDays(date);
        int day = date.getDay();
        int startIndex = day;
        int listIndex = 0;
        for (int i = 0; i < days; ++i) {
            if (startIndex > (ONE_WEEK - 1)) {
                startIndex = 0;
                listIndex++;
            }
            ViewHolder viewHolder = viewHolderList.get(listIndex);
            List<ViewHolderChild> viewHolderChildList = viewHolder.viewHolderChildList;
            ViewHolderChild viewHolderChild = viewHolderChildList.get(startIndex);

            int days1 = date.getDate();
            String daysStr = String.valueOf(days1);
            try {
                long timeKey = date.getTime();
                if (vacationList.size() > 0 && vacationList.containsKey(timeKey)) {
                    long vacationTime = vacationList.get(timeKey);
                    if (vacationTime > 0) {
                        daysStr = daysStr + VACATION;
                        SpannableString span = new SpannableString(daysStr);
                        span.setSpan(new RelativeSizeSpan(0.5f), 1, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        ForegroundColorSpan spanColor = new ForegroundColorSpan(Color.rgb(50, 192, 196));
                        span.setSpan(spanColor, 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        viewHolderChild.textViewDay.setText(span);
                    }
                } else {
                    viewHolderChild.textViewDay.setText(daysStr);
                }
            } catch (Exception e) {
                Log.e(LOG, "set date error", e);
            }


            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateTime = simpleDateFormat.format(date);
            viewHolderChild.linearLayout.setTag(dateTime);

            if (days1 == today) {
                viewHolderChild.textViewDay.setBackgroundDrawable(drawableCurrentDayBg);
                viewHolderChild.textViewDay.setTextColor(Color.WHITE);
            } else {
                viewHolderChild.textViewDay.setBackgroundDrawable(drawableNormal);
                viewHolderChild.textViewDay.setTextColor(Color.BLACK);
            }

            if (selectYear == (date.getYear() + 1900) && selectMOnth == (date.getMonth() + 1) && selectDay == date.getDate() && today != selectDay) {
                viewHolderChild.textViewDay.setBackgroundDrawable(drawableClickBg);
                viewHolderChild.textViewDay.setTextColor(getResources().getColor(android.R.color.black));
                lastClickView = viewHolderChild.textViewDay;
            }


            days1 = days1 + 1;
            date.setDate(days1);

            startIndex++;



            /***获取农历日期 start***/
            String chinesMonth = null;
            String lunar = CalendarUtil.getChineseDay(date.getYear() + 1900, date.getMonth() + 1, date.getDate());
            if (lunar.equals(CalendarUtil.getChineseDay(0))) {
                chinesMonth = CalendarUtil.getChineseMonth(date.getYear() + 1900, date.getMonth() + 1, date.getDate());
            }
            if (chinesMonth != null && !chinesMonth.isEmpty()) {
                if (chinesMonth.equals(CalendarUtil.getChineseMonth(0))) {
                    viewHolderChild.textViewLunar.setText(SPRING);
                } else {
                    viewHolderChild.textViewLunar.setText(chinesMonth);
                }
            } else {
                viewHolderChild.textViewLunar.setText(lunar);
            }
            /***获取农历日期 start***/
            /***获取二十四节气 start***/
            try {
                int month1 = date.getMonth();
                if (month1 == 0) {
                    month1 = 12;
                }
                CalendarUtil.setGregorian(date.getYear() + 1900, month1, date.getDate());
                CalendarUtil.computeChineseFields();
                CalendarUtil.computeSolarTerms();
                String chilneseDate = CalendarUtil.getDateString();
                if (chilneseDate != null && !chilneseDate.isEmpty()) {
                    viewHolderChild.textViewLunar.setText(chilneseDate);
                }
            } catch (Exception e) {
                Log.e(LOG, "get chinese date error!", e);
            }
            /***获取二十四节气 end***/
            /**获取除夕 start**/
            chinesMonth = CalendarUtil.getChineseMonth(date.getYear() + 1900, date.getMonth() + 1, date.getDate());
            String chineseDate = CalendarUtil.getChineseDay(28);
            if (chinesMonth != null && chinesMonth.equals(CalendarUtil.getChineseMonth(11))) {
                if (lunar.equals(chineseDate)) {
                    viewHolderChild.textViewLunar.setText(CHUXI);
                }
            }
            /**获取除夕 end**/
        }
        currentMonth = month;
        currentYear = year;
    }

    /**
     * 清除所有的日期
     */
    private void clear() {
        for (int i = 0; i < WEEKS; ++i) {
            ViewHolder viewHolder = viewHolderList.get(i);
            List<ViewHolderChild> viewHolderChildList = viewHolder.viewHolderChildList;
            for (int j = 0; j < ONE_WEEK; ++j) {
                ViewHolderChild viewHolderChild = viewHolderChildList.get(j);
                viewHolderChild.textViewDay.setText("");
                viewHolderChild.textViewLunar.setText("");
                viewHolderChild.linearLayout.setTag(null);
                viewHolderChild.textViewDay.setBackgroundDrawable(drawableNormal);
                viewHolderChild.textViewLunar.setTextColor(Color.BLACK);
                viewHolderChild.linearLayout.setBackgroundDrawable(drawableNormal);
            }
        }
        if (lastClickView != null) {
            lastClickView.setBackgroundDrawable(drawableNormal);
        }
    }

    /**
     * 设置选中的日期
     * @param year
     * @param month 1月到12月
     * @param day
     */
    public void setSelectDate(int year, int month, int day) {
        selectYear = year;
        selectMOnth = month;
        selectDay = day;
        currentYear = year - 1900;
        currentMonth = month - 1;
        initCalendarDays(currentYear, currentMonth);

        Date date = new Date();
        date.setMonth(month - 1);
        date.setDate(day);
        date.setYear(year - 1900);
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = simpleDateFormat.format(date);
        if (clickDateListener != null) {
            clickDateListener.clickDate(dateTime);
        }
    }

    /**
     * 设置假期日期
     * @param year 如2015
     * @param month 1月到十二月
     * @param startDate 开始日期 1号到31号
     * @param endDate 结束日期 1号到31号
     */
    public void setVacationDate(int year, int month, int startDate, int endDate) throws Exception {
        if (startDate > endDate) {
            throw new Exception("开始时间不能大于结束时间.");
        }
        int newYear = year - 1900;
        int newMonth = month - 1;
        Date date = new Date();
        date.setTime(0);
        date.setYear(newYear);
        date.setMonth(newMonth);
        int days = getMonthDays(date);
        if (startDate > days) {
            throw new Exception("设置的假期开始天不在设置的月份里面.");
        }
        if (endDate > days) {
            throw new Exception("设置的假期结束天不在设置的月份里面.");
        }
        date.setDate(startDate);
        days = endDate - startDate + 1;
        for (int i = 0; i < days; ++i) {
            vacationList.put(date.getTime(), date.getTime());
            startDate = startDate + 1;
            date.setDate(startDate);
        }
        initCalendarDays(currentYear, currentMonth);
    }

    /**设置点击日期监听接口**/
    public void setOnClickDateListener(ClickDateListener clickDateListener) {
        this.clickDateListener = clickDateListener;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            motionEventDownX = event.getX();
        } else if (action == MotionEvent.ACTION_UP) {
            float motionEventUpX = event.getX();
            float po = motionEventDownX - motionEventUpX;
            po = Math.abs(po);
            if (po < 5) {//如果触摸屏幕的位置小于特定值,则视为点击事件
                onClick(v);
                return false;
            }
            if ((motionEventUpX - motionEventDownX) >= INTERVAL_X) {//turn righr
                if (currentMonth <= 0) {//如果当前显示的一经是一月份,则将当前月份置为11月,年份减一年
                    currentMonth = 11;
                    currentYear = currentYear - 1;
                } else {//如果当前显示的月份不是一月，则显示上一个月份日期
                    currentMonth = currentMonth - 1;
                }
                initCalendarDays(currentYear, currentMonth);
            } else if ((motionEventDownX - motionEventUpX) >= INTERVAL_X) {//turn left
                if (currentMonth >= 11) {//如果当前显示的一经是十二月份,则将当前月份置为1月,年份加一年
                    currentMonth = 0;
                    currentYear = currentYear + 1;
                } else {//如果当前显示的月份不是十二月，则显示下一个月份日期
                    currentMonth = currentMonth + 1;
                }
                initCalendarDays(currentYear, currentMonth);
            }
        }
        return true;
    }

    @Override
    public void onClick(View v) {
       try {
           String formatterTime = (String) v.getTag();
           if (formatterTime != null && clickDateListener != null) {
               TextView textView =  (TextView)((ViewGroup) v).getChildAt(0);
               if (lastClickView != null) {
                   TextView textViewLast = (TextView) lastClickView;
                   String currentDay = textView.getText().toString().trim();
                   String textDay = textViewLast.getText().toString().trim();
                   Date currentDate = new Date();
                   if (!TextUtils.isEmpty(currentDay) && !TextUtils.isEmpty(textDay) &&
                           !currentDay.equals(textDay) && Integer.valueOf(textDay) == currentDate.getDate()) {//如果是当前日期天数
                       lastClickView.setBackgroundDrawable(drawableCurrentDayBg);
                       textViewLast.setTextColor(Color.WHITE);
                   } else {
                       lastClickView.setBackgroundDrawable(drawableNormal);
                       textViewLast.setTextColor(Color.BLACK);
                   }
               }
               textView.setBackgroundDrawable(drawableClickBg);
               textView.setTextColor(Color.WHITE);
               lastClickView = textView;
               clickDateListener.clickDate(formatterTime);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    private class ViewHolder {
        public LinearLayout linearLayoutMain;
        public List<ViewHolderChild> viewHolderChildList;
    }

    private class ViewHolderChild {
        public LinearLayout linearLayout;
        public TextView textViewDay;
        public TextView textViewLunar;
    }

    /**
     * 点击日期监听接口
     */
    public interface ClickDateListener {
        /**
         * yyyy-MM-dd HH:mm:ss
         * @param formatterTime
         */
        public void clickDate(String formatterTime);
    }


}

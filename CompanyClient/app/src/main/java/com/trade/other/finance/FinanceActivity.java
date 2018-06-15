package com.trade.other.finance;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.architecture.mvp.lce.BaseLceActivity;
import com.trade.R;
import com.trade.model.FinanceResultBean;
import com.trade.other.finance.di.DaggerFinanceComponent;
import com.trade.other.finance.di.FinanceModule;
import com.trade.other.finance.mvp.FinancePresenter;
import com.trade.other.finance.mvp.FinanceView;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;

public class FinanceActivity extends BaseLceActivity<FrameLayout, FinanceResultBean, FinanceView, FinancePresenter> implements FinanceView {

    private static final String TAG = "FinanceActivity";
    private String year;
    private static FinanceResultBean financeResultBean;
    public static Intent getCallingIntent(Context context, String year) {
        Intent intent = new Intent(context, FinanceActivity.class);
        intent.putExtra("year", year);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        year = getIntent().getStringExtra("year");
        super.onCreate(savedInstanceState);

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().add(R.id.contentView, new PlaceholderFragment()).commit();
//        }
    }

    @Override
    protected void init() {
        super.init();
        loadData(false);
    }

    @Override
    public void initializeInjector() {
        DaggerFinanceComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .financeModule(new FinanceModule())
                .build()
                .inject(this);
    }

    @Override
    public ViewDataBinding createViewDataBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_line_column_dependency);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return "该年份无财务报表~";
    }

    @Override
    public void setData(FinanceResultBean data) {
        financeResultBean = data;
        getSupportFragmentManager().beginTransaction().add(R.id.contentView, new PlaceholderFragment()).commit();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadData(pullToRefresh, year);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        public final static String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
                "Sep", "Oct", "Nov", "Dec",};

        //        public final static String[] days = new String[]{"Mon", "Tue", "Wen", "Thu", "Fri", "Sat", "Sun",};
        public final static String[] days = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
                "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};

        private LineChartView chartTop;
        private ColumnChartView chartBottom;

        private LineChartData lineData;
        private ColumnChartData columnData;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_line_column_dependency, container, false);

            // *** TOP LINE CHART ***
            chartTop = (LineChartView) rootView.findViewById(R.id.chart_top);

            // Generate and set data for line chart
            generateInitialLineData();

            // *** BOTTOM COLUMN CHART ***

            chartBottom = (ColumnChartView) rootView.findViewById(R.id.chart_bottom);

            generateColumnData();

            return rootView;
        }

        private void generateColumnData() {

            int numSubcolumns = 1;
            int numColumns = months.length;

//            int max = getMonthMaxValue();
            List<FinanceResultBean.ResultBean.FinanceMonthBean> list = financeResultBean.getResult().getFinances();

            List<AxisValue> axisValues = new ArrayList<AxisValue>();
            List<Column> columns = new ArrayList<Column>();
            List<SubcolumnValue> values;
            for (int i = 0; i < numColumns; ++i) {

                values = new ArrayList<SubcolumnValue>();
                for (int j = 0; j < numSubcolumns; ++j) {
//                    double unit = 1000;
                    double price = list.get(i).getProfit();
                    values.add(new SubcolumnValue((float) price, ChartUtils.COLOR_ORANGE));
                }

                axisValues.add(new AxisValue(i).setLabel(months[i]));

                columns.add(new Column(values).setHasLabelsOnlyForSelected(true));
            }

            columnData = new ColumnChartData(columns);

            columnData.setAxisXBottom(new Axis(axisValues).setHasLines(true));
            columnData.setAxisYLeft(new Axis().setHasLines(true).setMaxLabelChars(2));

            chartBottom.setColumnChartData(columnData);

            // Set value touch listener that will trigger changes for chartTop.
            chartBottom.setOnValueTouchListener(new ValueTouchListener());

            // Set selection mode to keep selected month column highlighted.
            chartBottom.setValueSelectionEnabled(true);

//            Viewport v = new Viewport(0, (float) maxValue, 30, (float) -maxValue);
//            chartBottom.setMaximumViewport(v);

            chartBottom.setZoomType(ZoomType.HORIZONTAL);

            // chartBottom.setOnClickListener(new View.OnClickListener() {
            //
            // @Override
            // public void onClick(View v) {
            // SelectedValue sv = chartBottom.getSelectedValue();
            // if (!sv.isSet()) {
            // generateInitialLineData();
            // }
            //
            // }
            // });

        }

        /**
         * Generates initial data for line chart. At the begining all Y values are equals 0. That will change when user
         * will select value on column chart.
         */
        private void generateInitialLineData() {
            int numValues = 31;
//            double maxValue = getMaxValue();
            List<AxisValue> axisValues = new ArrayList<AxisValue>();

            Line lineInBill = generateInBillLine();
            Line lineOutBill = generateOutBillLine();
//            List<PointValue> values = new ArrayList<PointValue>();
            for (int i = 0; i < numValues; ++i) {
//                values.add(new PointValue(i, 0));
                axisValues.add(new AxisValue(i).setLabel(days[i]));
            }

//            Line line = new Line(values);
//            line.setColor(ChartUtils.COLOR_GREEN).setCubic(true);

            List<Line> lines = new ArrayList<Line>();
            lines.add(lineInBill);
            lines.add(lineOutBill);

            lineData = new LineChartData(lines);
            lineData.setAxisXBottom(new Axis(axisValues).setHasLines(true));
            lineData.setAxisYLeft(new Axis().setHasLines(true).setMaxLabelChars(3));

            chartTop.setLineChartData(lineData);

            // For build-up animation you have to disable viewport recalculation.
            chartTop.setViewportCalculationEnabled(false);

            // And set initial max viewport and current viewport- remember to set viewports after data.
            Viewport v = new Viewport(0, (float) 99, 30, (float) 0);
            chartTop.setMaximumViewport(v);
            chartTop.setCurrentViewport(v);

            chartTop.setZoomType(ZoomType.HORIZONTAL);
        }

        private double getMaxValue() {
            double max = financeResultBean.getResult().getFinances().get(0).getFinances().get(0).getInBillPrice();
            for (FinanceResultBean.ResultBean.FinanceMonthBean monthBean :
                    financeResultBean.getResult().getFinances()) {
                for (FinanceResultBean.ResultBean.FinanceMonthBean.FinanceBean bean :
                        monthBean.getFinances()) {

                    if (bean.getInBillPrice() > bean.getOutBillPrice()) {
                        max = Math.max(max, bean.getInBillPrice());
                    } else {
                        max = Math.max(max, bean.getOutBillPrice());
                    }
                }
            }
            return max;
        }

        private Line generateOutBillLine() {
            List<PointValue> values = new ArrayList<PointValue>();
            for (int i = 0; i < 31; ++i) {
                values.add(new PointValue(i, 0));
            }

            Line line = new Line(values);
            line.setColor(ChartUtils.COLOR_RED).setCubic(true);
            return line;
        }

        private Line generateInBillLine() {
            List<PointValue> values = new ArrayList<PointValue>();
            for (int i = 0; i < 31; ++i) {
                values.add(new PointValue(i, 0));
            }

            Line line = new Line(values);
            line.setColor(ChartUtils.COLOR_GREEN).setCubic(true);
            return line;
        }

        private void generateLineData(int columnIndex) {
            // Cancel last animation if not finished.
            chartTop.cancelDataAnimation();

            List<FinanceResultBean.ResultBean.FinanceMonthBean.FinanceBean> list = financeResultBean.getResult().getFinances().get(columnIndex).getFinances();
            // Modify data targets 进货账单 生成当月每天的点
            Line line = lineData.getLines().get(0);// For this example there is always only one line.
            List<PointValue> values = line.getValues();
            for (int i = 0; i < list.size(); i++) {
                // Change target only for Y value.
//                int p = (int) (Math.random() * 2);
//                int x = p < 1 ? -1 : 1;
//                double unit = 1000;
                double price = list.get(i).getInBillPrice();
                values.get(i).setTarget(values.get(i).getX(), (float) price);
            }
            Line lineOut = lineData.getLines().get(1);// For this example there is always only one line.
            List<PointValue> valuesOut = lineOut.getValues();
            for (int i = 0; i < list.size(); i++) {
                // Change target only for Y value.
                //                int p = (int) (Math.random() * 2);
                //                int x = p < 1 ? -1 : 1;
//                double unit = 1000;
                double price = list.get(i).getOutBillPrice();
                valuesOut.get(i).setTarget(valuesOut.get(i).getX(), (float) price);
            }
            // Start new data animation with 300ms duration;
            chartTop.startDataAnimation(300);
        }

        private void resetLineData() {
            // Cancel last animation if not finished.
            chartTop.cancelDataAnimation();

            // Modify data targets
            Line line = lineData.getLines().get(0);// For this example there is always only one line.
//            line.setColor(ChartUtils.COLOR_GREEN);
            for (PointValue value : line.getValues()) {
                // Change target only for Y value.
                value.setTarget(value.getX(), 0);
            }
            Line lineOut = lineData.getLines().get(1);// For this example there is always only one line.
//            lineOut.setColor(ChartUtils.COLOR_GREEN);
            for (PointValue value : lineOut.getValues()) {
                // Change target only for Y value.
                value.setTarget(value.getX(), 0);
            }
            // Start new data animation with 300ms duration;
            chartTop.startDataAnimation(300);
        }
        private class ValueTouchListener implements ColumnChartOnValueSelectListener {

            @Override
            public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
                generateLineData(columnIndex);
            }

            @Override
            public void onValueDeselected() {

                resetLineData();

            }
        }
    }
}

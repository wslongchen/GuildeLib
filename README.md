# GuildeLib
这是一个安卓引导界面的lib，拿来即用 在主Activity里oncreate中写下如下

    GuideContoler contoler = new GuideContoler(this);
    //contoler.setmShapeType(ShapeType.RECT);//设置指示器的形状为矩形，默认是圆形
    int[] imgIds = {R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
    LayoutInflater inflater = LayoutInflater.from(this);
    View view = inflater.inflate(R.layout.pager_four,null);
    contoler.init(imgIds, view);
    view.findViewById(R.id.bt_login).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            Toast.makeText(getApplicationContext(), "进入", Toast.LENGTH_LONG).show();
        }
    });
    并做好相应的布局文件即可

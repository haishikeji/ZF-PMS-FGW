<template>
  <div class="viewport">
        <!-- 左侧数据部分 -->
    <div class="left-part">
        <div class="chart1">
            <div class="title">
                <div class="title-img">
                    <img src="../assets/images/titleimg.png">
                </div>
                <div class="title-txt">
                    <h1>
                        返乡人数变化趋势
                    </h1>
                </div>
                <div class="title-time">
                    <p>
                        截止：{{ recordDate }}
                    </p>
                </div>
            </div>
            <div class="line">
                <img src="../assets/images/line.png">
            </div>
            <div class="nav1">
                <div class="people" id="people" ref = "people">

                </div>
            </div>
        </div>
        <div class="chart2">
            <div class="title">
                <div class="title-img">
                    <img src="../assets/images/titleimg.png">
                </div>
                <div class="title-txt">
                    <h1>
                        返乡用户类型
                    </h1>
                </div>
                <div class="title-time">
                    <p>
                        截止：{{ recordDate }}
                    </p>
                </div>
            </div>
            <div class="line">
                <img src="../assets/images/line.png">
            </div>
            <div class="nav2">
                <div class="numdata" id="numdata" ref="numdata">
                
                </div>
            </div>
        </div>
    </div>


    <!-- 中间数据部分 -->
    <div class="center-part">
        <div class="map">
            <div class="geo" id="geo" ref="geo">

            </div>
        </div>
        <div class="table">
            <div class="chart3" id="chart3" ref="chart3">
            </div>
        </div>
    </div>

    <!-- 右侧数据部分 -->
    <div class="right-part">
        <div class="chart4">
            <div class="title-right">
                <div class="title-img-right">
                    <img src="../assets/images/titleimg.png">
                </div>
                <div class="title-txt-right">
                    <h1>
                        返乡省份排名
                    </h1>
                </div>
                <div class="title-time-right">
                    <p>
                        截止：{{ recordDate }}
                    </p>
                </div>
            </div>
            <div class="line-right">
                <img src="../assets/images/line.png">
            </div>
            <!-- <div class="nav">
                <p class="gover">省份</p><p class="txtline">|</p><p class="people-num">人数</p>
            </div>
            <div class="nav3">
                <div class="total" id="total">

                </div>
            </div> -->
            <table
              class="table1"
              width="100%"
              border="0"
              cellspacing="0"
              cellpadding="0"
              height='100%'
            >
              <tbody>
                <tr class="titleline">
                  <th scope="col">排名</th>
                  <th scope="col">省份</th>
                  <th scope="col">返乡人数</th>
                </tr>
                <tr v-for="(item, index) in returnRankList">
                  <td><span>{{item.rank}}</span></td>
                  <td>{{item.name}}</td>
                  <td>{{item.total}}<br /></td>
                </tr>
                <!-- <tr>
                  <td><span>2</span></td>
                  <td>北京市</td>
                  <td>9879</td>
                </tr>
                <tr>
                  <td><span>3</span></td>
                  <td>云南省</td>
                  <td>9010</td>
                </tr>
                <tr>
                  <td><span>4</span></td>
                  <td>广东省</td>
                  <td>8569</td>
                </tr>
                <tr>
                  <td><span>5</span></td>
                  <td>江苏省</td>
                  <td>7865</td>
                </tr>
                <tr>
                  <td><span>6</span></td>
                  <td>四川省</td>
                  <td>6899</td>
                </tr> -->
              </tbody>
            </table>
        </div>
        
        <div class="chart5">
            <div class="title-right">
                <div class="title-img-right">
                    <img src="../assets/images/titleimg.png">
                </div>
                <div class="title-txt-right">
                    <h1>
                        返乡接触情况
                    </h1>
                </div>
                <div class="title-time-right">
                    <p>
                        截止：{{ recordDate }}
                    </p>
                </div>
            </div>
            <div class="line-right">
                <img src="../assets/images/line.png">
            </div>
            <div class="chart5-part">
                <div class="nav4">
                    <div class="contact" id="contact" ref = "contact">
                    </div>
                </div>
                <div class="contact-table">
                    <table>
                        <tr>
                            <th></th>
                            <th class="row1">日数量</th>
                            <th class="row1">月数量</th>
                        </tr>
                        <tr>
                            <th class="row1">返乡</th>
                            <th class="row2">{{dayContact.total}}</th>
                            <th class="row2">{{mouthContact.total}}</th>
                        </tr>
                        <tr>
                            <th class="row1">接触</th>
                            <th class="row2">{{dayContact.contact}}</th>
                            <th class="row2">{{mouthContact.contact}}</th>
                        </tr>
                        <tr>
                            <th class="row1">占比</th>
                            <th class="row2">{{dayContact.contactPercentage}}%</th>
                            <th class="row2">{{mouthContact.contactPercentage}}%</th>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <!-- 底部数据 -->
    <!-- <div class = "footer"> <p>业务指导：南昌移动市场部 技术支持：南昌移动IT科创团队</p></div> -->
  </div>
    
    <!-- <footer>
        <p>业务指导：南昌移动市场部 技术支持：南昌移动IT科创团队</p>
    </footer> -->
</template>

<script>
import * as echarts from 'echarts'
import { getHomeData } from "@/api/springmarketing/returndetail"
import "@/api/databoard/china1.js"
// import "@/api/databoard/center2.js"
export default {
  name: "Databoard",
  data() {
    return {
      recordDate: '2023-12-22',
      returnRankList :  [
        {
            "rank": 1,
            "name": "北京",
            "total": 367
        },
        {
            "rank": 1,
            "name": "上海",
            "total": 300
        },
        {
            "rank": 1,
            "name": "天津",
            "total": 256
        },
        {
            "rank": 1,
            "name": "重庆",
            "total": 208
        },
        {
            "rank": 1,
            "name": "江苏",
            "total": 173
        },
        {
            "rank": 1,
            "name": "云南",
            "total": 123
        }
      ],
      beforeYearData: [120, 132, 101, 134, 90, 230, 210, 101, 134, 90, 230, 210],
      currentYearData: [1,1,1,1,1,1,1,1,1,1,1,1],
      returnTypeList: [{
            "name": "省内返乡",
            "value": 250
        },
        {
            "name": "外省返乡",
            "value": 580
      }],
      dayContact: {
          "name": "当日接触率",
          "total": 100,
          "contact": 89,
          "contactPercentage": 89.0
      },
      mouthContact:  {
          "name": "当月接触率",
          "total": 2000,
          "contact": 1000,
          "contactPercentage": 50.0
      },
      dayBusiness:  [100,50,120,200],
      mouthBusiness: [1700,500,1200,2000],
      areaTree : [{
          "name": "中国",
          "total": {
              "back": 82892,
              "todayback": 9920,
              "contact": 50897,
              "todaycontact": 2565,
              "contactRate": 50,
              "showRate": true,
              "todayContactRate":23,
              "showContactRate": true
          },
          "children": [{
              "name": "湖北",
              "total": {
                  "back": 1316,
                  "todayback": 124
              }
          }, {
              "name": "香港",
              "total": {
                  "back": 98,
                  "todayback": 4
              }
          }, {
              "name": "台湾",
              "total": {
                  "back": 0,
                  "todayback": 0
              }
          }, {
              "name": "上海",
              "total": {
                  "back": 548,
                  "todayback": 23
              }
          }, {
              "name": "北京",
              "total": {
                  "back": 3459,
                  "todayback": 233
              }
          }, {
              "name": "广东",
              "total": {
                  "back": 6449,
                  "todayback": 379
              }
          }, {
              "name": "福建",
              "total": {
                  "back": 3908,
                  "todayback": 290
              }
          }, {
              "name": "内蒙古",
              "total": {
                  "back": 56,
                  "todayback": 2
              }
          }, {
              "name": "天津",
              "total": {
                  "back": 588,
                  "todayback": 76
              }
          }, {
              "name": "澳门",
              "total": {
                  "back": 19,
                  "todayback": 0
              }
          }, {
              "name": "浙江",
              "total": {
                  "back": 9841,
                  "todayback": 499
              }
          }, {
              "name": "山东",
              "total": {
                  "back": 439,
                  "todayback": 28
              }
          }, {
              "name": "辽宁",
              "total": {
                  "back": 156,
                  "todayback": 8
              }
          }, {
              "name": "云南",
              "total": {
                  "back": 342,
                  "todayback": 12
              }
          }, {
              "name": "河北",
              "total": {
                  "back": 123,
                  "todayback": 1
              }
          }, {
              "name": "黑龙江",
              "total": {
                  "back": 36,
                  "todayback": 3
              }
          }, {
              "name": "陕西",
              "total": {
                  "back": 13,
                  "todayback": 0
              }
          }, {
              "name": "甘肃",
              "total": {
                  "back": 5,
                  "todayback": 1
              }
          }, {
              "name": "吉林",
              "total": {
                  "back": 22,
                  "todayback": 0
              }
          }, {
              "name": "重庆",
              "total": {
                  "back": 131,
                  "todayback": 12
              }
              
          }, {
              "name": "河南",
              "total": {
                  "back": 416,
                  "todayback": 24
              }
          }, {
              "name": "湖南",
              "total": {
                  "back": 555,
                  "todayback": 12
              }
          }, {
              "name": "江西",
              "total": {
                  "back": 2367,
                  "todayback": 146
              }
          }, {
              "name": "贵州",
              "total": {
                  "back": 10,
                  "todayback": 0
              }
          }, {
              "name": "广西",
              "total": {
                  "back": 1316,
                  "todayback": 124
              }
          }, {
              "name": "安徽",
              "total": {
                  "back": 2908,
                  "todayback": 333
              }
          }, {
              "name": "海南",
              "total": {
                  "back": 3,
                  "todayback": 1
              }
          }, {
              "name": "宁夏",
              "total": {
                  "back": 16,
                  "todayback": 2
              }
          }, {
              "name": "新疆",
              "total": {
                  "back": 0,
                  "todayback": 0
              }
          }, {
              "name": "西藏",
              "total": {
                  "back": 0,
                  "todayback": 0
              }
          }, {
              "name": "青海",
              "total": {
                  "back": 68,
                  "todayback":4
              }
          }]
      }],
      geoCoordMap: {
        '新疆玛纳斯基地': [86.22, 44.30],
        '九江': [116.00, 29.70],
        '吉安': [115.2046, 26.3633],
        '新乡': [116.402217, 35.311657],
        ' ': [79.92, 37.12],
        '  ': [86.85, 47.70],
        '若羌县': [88.17, 39.02],
        '上海': [121.4648, 31.2891],
        '东莞': [113.8953, 22.901],
        '东营': [118.7073, 37.5513],
        '中山': [113.4229, 22.478],
        '临汾': [111.4783, 36.1615],
        '临沂': [118.3118, 35.2936],
        '丹东': [124.541, 40.4242],
        '丽水': [119.5642, 28.1854],
        '乌鲁木齐': [87.9236, 43.5883],
        '佛山': [112.8955, 23.1097],
        '保定': [115.0488, 39.0948],
        '兰州': [103.5901, 36.3043],
        '包头': [110.3467, 41.4899],
        '北京': [116.4551, 40.2539],
        '北海': [109.314, 21.6211],
        '南京': [118.8062, 31.9208],
        '南宁': [108.479, 23.1152],
        '南昌': [116.0046, 28.6633],
        '南通': [121.1023, 32.1625],
        '厦门': [118.1689, 24.6478],
        '台州': [121.1353, 28.6688],
        '合肥': [117.29, 32.0581],
        '呼和浩特': [111.4124, 40.4901],
        '咸阳': [108.4131, 34.8706],
        '哈尔滨': [127.9688, 45.368],
        '唐山': [118.4766, 39.6826],
        '嘉兴': [120.9155, 30.6354],
        '大同': [113.7854, 39.8035],
        '大连': [122.2229, 39.4409],
        '天津': [117.4219, 39.4189],
        '太原': [112.3352, 37.9413],
        '威海': [121.9482, 37.1393],
        '宁波': [121.5967, 29.6466],
        '宝鸡': [107.1826, 34.3433],
        '宿迁': [118.5535, 33.7775],
        '常州': [119.4543, 31.5582],
        '广州': [113.5107, 23.2196],
        '廊坊': [116.521, 39.0509],
        '延安': [109.1052, 36.4252],
        '张家口': [115.1477, 40.8527],
        '徐州': [117.5208, 34.3268],
        '德州': [116.6858, 37.2107],
        '惠州': [114.6204, 23.1647],
        '成都': [103.9526, 30.7617],
        '扬州': [119.4653, 32.8162],
        '承德': [117.5757, 41.4075],
        '拉萨': [91.1865, 30.1465],
        '无锡': [120.3442, 31.5527],
        '日照': [119.2786, 35.5023],
        '昆明': [102.9199, 25.4663],
        '杭州': [119.5313, 29.8773],
        '枣庄': [117.323, 34.8926],
        '柳州': [109.3799, 24.9774],
        '株洲': [113.5327, 27.0319],
        '武汉': [114.3896, 30.6628],
        '汕头': [117.1692, 23.3405],
        '江门': [112.6318, 22.1484],
        '沈阳': [123.1238, 42.1216],
        '沧州': [116.8286, 38.2104],
        '河源': [114.917, 23.9722],
        '泉州': [118.3228, 25.1147],
        '泰安': [117.0264, 36.0516],
        '泰州': [120.0586, 32.5525],
        '济南': [117.1582, 36.8701],
        '济宁': [116.8286, 35.3375],
        '海口': [110.3893, 19.8516],
        '淄博': [118.0371, 36.6064],
        '淮安': [118.927, 33.4039],
        '深圳': [114.5435, 22.5439],
        '清远': [112.9175, 24.3292],
        '温州': [120.498, 27.8119],
        '渭南': [109.7864, 35.0299],
        '湖州': [119.8608, 30.7782],
        '湘潭': [112.5439, 27.7075],
        '滨州': [117.8174, 37.4963],
        '潍坊': [119.0918, 36.524],
        '烟台': [120.7397, 37.5128],
        '玉溪': [101.9312, 23.8898],
        '珠海': [113.7305, 22.1155],
        '盐城': [120.2234, 33.5577],
        '盘锦': [121.9482, 41.0449],
        '石家庄': [114.4995, 38.1006],
        '福州': [119.4543, 25.9222],
        '秦皇岛': [119.2126, 40.0232],
        '绍兴': [120.564, 29.7565],
        '聊城': [115.9167, 36.4032],
        '肇庆': [112.1265, 23.5822],
        '舟山': [122.2559, 30.2234],
        '苏州': [120.6519, 31.3989],
        '莱芜': [117.6526, 36.2714],
        '菏泽': [115.6201, 35.2057],
        '营口': [122.4316, 40.4297],
        '葫芦岛': [120.1575, 40.578],
        '衡水': [115.8838, 37.7161],
        '衢州': [118.6853, 28.8666],
        '西宁': [101.4038, 36.8207],
        '西安': [109.1162, 34.2004],
        '贵阳': [106.6992, 26.7682],
        '连云港': [119.1248, 34.552],
        '邢台': [114.8071, 37.2821],
        '邯郸': [114.4775, 36.535],
        '郑州': [113.4668, 34.6234],
        '鄂尔多斯': [108.9734, 39.2487],
        '重庆': [107.7539, 30.1904],
        '金华': [120.0037, 29.1028],
        '铜川': [109.0393, 35.1947],
        '银川': [106.3586, 38.1775],
        '镇江': [119.4763, 31.9702],
        '长春': [125.8154, 44.2584],
        '长沙': [113.0823, 28.2568],
        '长治': [112.8625, 36.4746],
        '阳泉': [113.4778, 38.0951],
        '青岛': [120.4651, 36.3373],
        '韶关': [113.7964, 24.7028]
      },
      // SHData: [
      //   [{
      //       name: '南昌'
      //   }, {
      //       name: '南昌',
      //       value: 100
      //   }],

      //   [{
      //       name: '南昌'
      //   }, {
      //       name: '北京',
      //       value: 95.26
      //   }],
      //   [{
      //       name: '南昌'
      //   }, {
      //       name: '上海',
      //       value: 30
      //   }],
      //   [{
      //       name: '南昌'
      //   }, {
      //       name: '呼和浩特',
      //       value: 20
      //   }],
      //   [{
      //       name: '南昌'
      //   }, {
      //       name: '青岛',
      //       value: 70
      //   }],
      //   [{
      //       name: '南昌'
      //   }, {
      //       name: '沈阳',
      //       value: 60
      //   }],
      //   [{
      //       name: '南昌'
      //   }, {
      //       name: '深圳',
      //       value: 50
      //   }],
      //   [{
      //       name: '南昌'
      //   }, {
      //       name: '九江',
      //       value: 100
      //   }],
      //   [{
      //       name: '南昌'
      //   }, {
      //       name: '昆明',
      //       value: 100
      //   }],
      // ]
      SHData: [
        [{
            name: '南昌'
        }, {
            name: '南昌',
            value: 100
        }]
      ]
    };
  },
  beforeCreated(){

  },
  created() {
    this.getList()
    console.log("created"+this.currentYearData)
    
  },
  mounted() {
    this.peopleChart = echarts.init(this.$refs.people)
    this.initPeopleChart( this.peopleChart) 

    this.numdataChart = echarts.init(this.$refs.numdata)
    this.initReturnTypeChart( this.numdataChart) 

    this.contactChart = echarts.init(this.$refs.contact)
    this.initContactChart(this.contactChart)

    this.businessChart = echarts.init(this.$refs.chart3)
    this.initBusinessChart(this.businessChart)

    this.myMapChart = echarts.init(this.$refs.geo)
    this.initMyMapChart(this.myMapChart)
  },
  watch: {
    // 监听currentYearData变化
    currentYearData() {
      // 从新渲染ECharts图表
      let option = this.getPeopleChartOption()
      this.peopleChart.setOption(option)
    },
    // 监听beforeYearData变化
    beforeYearData() {
      // 从新渲染ECharts图表
      let option = this.getPeopleChartOption()
      this.peopleChart.setOption(option)
    },
    // 监听returnTypeList变化
    returnTypeList() {
      let option = this.getReturnTypeChartPieOption()
      this.numdataChart.setOption(option)
    },
    // 监听dayContact变化
    dayContact() {
      let option = this.getContactOption()
      this.contactChart.setOption(option)
    },
    // 监听mouthContact变化
    mouthContact() {
      let option = this.getContactOption()
      this.contactChart.setOption(option)
    },
    // 监听dayBusiness变化
    dayBusiness() {
      let option = this.getBusinessOption()
      this.businessChart.setOption(option)
    },
    // 监听mouthBusiness变化
    mouthBusiness() {
      let option = this.getBusinessOption()
      this.businessChart.setOption(option)
    },
    // 监听SHData变化
    SHData() {
      console.log("enter watch SHData")
      let option = this.getMyMapFlightOption()
      this.myMapChart.setOption(option)
    }
  },
  methods: {
    // 获取接口请求数据
    async getList() {
      getHomeData().then(response => {
        this.beforeYearData = response.beforeYearData
        this.currentYearData = response.currentYearData
        this.returnTypeList  = response.returnTypeList
        this.recordDate = response.recordDate
        this.returnRankList = response.returnRankList
        this.dayContact = response.dayContact
        this.mouthContact = response.mouthContact
        this.dayBusiness = response.dayBusiness
        this.mouthBusiness = response.mouthBusiness
        this.SHData = response.sHData
      });
    },
    // 获取返乡人数变化option
    getPeopleChartOption() {
      let option;
      option = { 
          tooltip: {
              trigger: 'axis'
          },
          grid: {
              left: '2%',
              right: '5%',
              bottom: '5%',            
              containLabel: true
          },
          xAxis: {
              type: 'category',
              boundaryGap: false,
              axisLabel:{
                  show:true,
                  textStyle:{
                      color:'#ffffff'
                  }
              },
              axisLine:{
                  lineStyle:{
                      color:'#ffffff',
                      width:1
                  }
              },
              data: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
          },
          yAxis: {
              type: 'value',
              name:'单位：人',
              nameTextStyle:{
                  color:'#ffffff'
              },
              axisLabel:{
                  show:true,
                  textStyle:{
                      color:'#ffffff'
                  }
              },
              axisLine:{
                  lineStyle:{
                      color:'#ffffff',
                      width:1
                  }
              }
          },
          series: [
              {
                  name: '去年返乡人数',
                  type: 'line',
                  data: this.beforeYearData,
                  itemStyle:{
                      normal:{
                          color: '#FFFF00',
                          lineStyle:{
                              color:'#FB8800'
                          }
                      }
                  }
              },
              {
                  name: '今年返乡人数',
                  type: 'line',
                  data: this.currentYearData,
              }
          ]
      };
      return option
    },
    // 初始化返乡人数变化option
    initPeopleChart(currentChart) {
      let option = this.getPeopleChartOption()
      currentChart.setOption(option)
    },
    
    // 获取返乡用户类型Lineoption
    getReturnTypeChartLineOption() {
      let inSideNum = 0
      let outSideNum = 0
      // if (this.returnTypeList.length == 2) 
      for (let i = 0; i < this.returnTypeList.length; i++) 
      {

        if (this.returnTypeList[i].name === "省内返乡") 
        {
          inSideNum = this.returnTypeList[i].value
        }

        if (this.returnTypeList[i].name === "外省返乡") 
        {
          outSideNum = this.returnTypeList[i].value
        }
        
         
      }
      let lineoption = {
        grid:{
          top:20,
          bottom:30,
          left:'center'
        },
        color: ['#FB8800','#9E0101'],
        xAxis: {
          type: 'category',
          data: ['省内返乡','省外返乡'],
          axisLabel:{
            show:true,
            textStyle:{
                color:'#ffffff'
            }
          },
          axisLine:{
              lineStyle:{
                  color:'#ffffff',
                  width:1
              }
          }
        },
        yAxis: {
          type: 'value',
          axisLabel:{
            show:true,
            textStyle:{
                color:'#ffffff'
            }
          },
          axisLine:{
              lineStyle:{
                  color:'#ffffff',
                  width:1
              }
          }
        },
        series: [
          {
            data: [inSideNum, outSideNum],
            type: 'bar',
            label: {
              show: true,
              position: 'inside',
              fontSize:18
            },
          }
        ]
      };
      return lineoption
    },
    
    // 获取返乡用户类型Pieoption
    getReturnTypeChartPieOption() {
      let pieoption = {
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          right:0,
          top:30,
          textStyle:{
            color:'#ffffff'
          }
        },
        color: ['#FB8800','#9E0101'],
        series: [
          {
            name: '返乡类型',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            label: {
              normal: {
                position: 'inside',
                formatter: '{c}',
                color: '#ffffff',
                fontSize: 18
              },
              show: true,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: 20,
                fontWeight: 'bold',
                color: '#ffffff',
                fontWeight:'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: this.returnTypeList
          }
        ]
      };
      return pieoption
      // var chartDom = document.getElementById('numdata');
      // var myChart = echarts.init(chartDom);
      
      // let currentOption = pieoption;
      // setInterval(function () {
      //   currentOption = currentOption === pieoption ? lineoption : pieoption;
      //   myChart.setOption(currentOption, true);
      // }, 3000);
      // myChart.setOption(currentOption);
    },

    // 初始化返乡用户类型
    initReturnTypeChart(currentChart) {
      // let option = this.getReturnTypeChartPieOption()
      let option = this.getReturnTypeChartLineOption()
      currentChart.setOption(option)
    },
    // 获取返乡接触情况Option
    getContactOption() {
        let gaugeData = [
        {
          value: this.mouthContact.contactPercentage,
          name: '当月接触率',
          title: {
            offsetCenter: ['0%', '-35%']
          },
          detail: {
            valueAnimation: true,
            offsetCenter: ['0%', '-10%']
          }
        },
        {
          value: this.dayContact.contactPercentage,
          name: '当日接触率',
          title: {
            offsetCenter: ['0%', '15%']
          },
          detail: {
            valueAnimation: true,
            offsetCenter: ['0%', '40%']
          }
        }
      ];
      let option = {
        series: [
          {
            type: 'gauge',
            radius: '90%',
            startAngle: 90,
            endAngle: -270,
            itemStyle:{
              normal:{
                color: function (colors) {
                  var colorList = ['#9E0101','#FB8800'];
                  return colorList[colors.dataIndex];
                }
              }
            },
            pointer: {
              show: false
            },
            progress: {
              show: true,
              overlap: false,
              roundCap: true,
              clip: false,
              itemStyle: {
                borderWidth: 1,
                borderColor: '#fff'
              }
            },
            axisLine: {
              show:true,
              color:'#179327',
              lineStyle: {
                width: 25
              }
            },
            splitLine: {
              show: false,
              distance: 0,
              length: 10
            },
            axisTick: {
              show: false
            },
            axisLabel: {
              show: false,
              distance: 50
            },
            data: gaugeData,
            title: {
              fontSize: 12,
              color:'#fff'
            },
            detail: {
              width: 40,
              height: 8,
              fontSize: 11,
              color: 'inherit',
              borderColor: 'inherit',
              borderRadius: 20,
              borderWidth: 1,
              formatter: '{value}%'
            }
          }
        ]
      };
      return option
    },
    // 初始化返乡接触情况echart
    initContactChart(currentChart) {
      let option = this.getContactOption()
      currentChart.setOption(option)
    },
    // 获取业务办理量option
    getBusinessOption() {
      let option = {
          color:['#FB8800','#AB0000'],
          tooltip: {
              trigger: 'axis'
          },
          grid:{
            bottom:20
          },
          toolbox: {
              iconStyle:{
                  borderColor:'#ffffff'
              },
              emphasis:{
                  iconStyle:{
                      borderColor:'#FFE600'
                  }
              },
              feature: {
                  dataView: { show: true, readOnly: false },
                  magicType: { show: true, type: ['line', 'bar'] },
                  restore: { show: true },
                  saveAsImage: { show: true }
              }
          },
          xAxis: [
              {
                  type: 'category',
                  data: ['等效新增', '等效宽带新增', '5G合约新增', 'CHN产品新增'],
                  axisLabel:{
                      show:true,
                      interval:0,
                      textStyle:{
                          color:'#ffffff'
                      }
                  },
                  axisLine:{
                      lineStyle:{
                          color:'#ffffff',
                          width:1
                      }
                  },
              }
          ],
          yAxis: [
              {
                  type: 'value',
                  name: '当日完成量',
                  min: 0,
                  max: 500,
                  interval: 100,
                  axisLabel:{
                      color:'#fff'
                  },
                  axisLine:{
                      lineStyle:{
                          color:'#fff',
                          width:1
                      }
                  }
              },
              {
                  type: 'value',
                  name: '当月完成量',
                  min: 0,
                  max: 5000,
                  interval: 1000,
                  axisLabel:{
                      color:'#fff'
                  },
                  axisLine:{
                      lineStyle:{
                          color:'#fff',
                          width:1
                      }
                  }
                  
              }
          ],
          series: [
              {
                  name: '当日完成量',
                  type: 'bar',
                  data: this.dayBusiness
              },
              {
                  name: '当月完成量',
                  type: 'line',
                  yAxisIndex: 1,
                  data: this.mouthBusiness
              }
          ]
      };
      return option
    },
    // 初始化业务办理量echart
    initBusinessChart(currentChart) {
      let option = this.getBusinessOption()
      currentChart.setOption(option)
    },
    // 获取流向地图option
    getMyMapOption() {
      let heatoption = {
          title: {
              text: '返乡数据展示',
              textStyle: {
                  color: 'white',
                  fontStyle: 'normal',
                  fontSize:20
              },
              left: 'center',
              top: '40px'

          },
          tooltip: {
              trigger: 'item'
          },
          visualMap: { // 左侧小导航图标
              show: true,
              x: 'left',
              y: 'bottom',
              orient:'horizontal',
              textStyle: {
                  fontSize: 12,
                  color:'#fff'
              },
              min: 0,
              max: 7000,
              text: ['High', 'Low'],
              calculable: true,
              inRange: {
                color: ['#FFB866','#FF8800','#E32728', '#9A160D']
              }
          },
          series: [{
              name: '返乡人数',
              type: 'map',
              mapType: 'china',
              zlevel:0,
              zoom:1.2,
              roam: true, // 禁用拖动和缩放
              itemStyle: { // 图形样式
                  normal: {
                      borderWidth: .5, //区域边框宽度
                      borderColor: '#F6FA31', //区域边框颜色
                      areaColor: "#FB8800", //区域颜色
                  },
                  emphasis: { // 鼠标滑过地图高亮的相关设置
                      borderWidth: 2,
                      borderColor: '#F6FA31',
                      areaColor: "#E32728",
                  },
                  label:{
                      show:false
                  }
              },
              data: []}]
      };

      var provinces = this.areaTree[0].children
      // 遍历每一个省自治区、直辖市
      for (var province of provinces) {
          // 将每个省的返乡数添加到配置项的data中
          heatoption.series[0].data.push({
              'name': province.name,
              'value': province.total.back
          })
      }
      return heatoption
    },
    // 获取流向地图option
    // getSHData() {
    //   return [['南昌', this.SHData]] || []
    // },
    getMyMapFlightOption() {
      let series = []
      let geoCoordMap = this.geoCoordMap
      let planePath = 'path://M.6,1318.313v-89.254l-319.9-221.799l0.073-208.063c0.521-84.662-26.629-121.796-63.961-121.491c-37.332-0.305-64.482,36.829-63.961,121.491l0.073,208.063l-319.9,221.799v89.254l330.343-157.288l12.238,241.308l-134.449,92.931l0.531,42.034l175.125-42.917l175.125,42.917l0.531-42.034l-134.449-92.931l12.238-241.308L1705'
      let convertData = function (data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            var dataItem = data[i];
            var fromCoord = geoCoordMap[dataItem[0].name];
            var toCoord = geoCoordMap[dataItem[1].name];
            if (fromCoord && toCoord && dataItem[1]) {
                res.push([{
                    coord: toCoord,
                    visualMap:false,
                    itemStyle:{
                        color:'#F6FA31'
                    }
                }, {
                    coord: fromCoord,
                    visualMap:false,
                    itemStyle:{
                        color:'#F6FA31'
                    }
                }]);
            }
        }
        return res;
    };
    [['南昌',this.SHData]].forEach(function (item, i) {
    series.push({
        name: item[0] + ' Top10',
        type: 'lines',
        zlevel: 1,
        effect: {
            show: true,
            period: 1,
            trailLength: 0.3, //飞线
            color: '#F6FA31',
            symbolSize: 3.5
        },
        lineStyle: {
            normal: {
                color: '#F6FA31',
                width: 0,
                curveness: 0.2
            }
        },
        data: convertData(item[1])
    }, {
        name: item[0],
        type: 'lines',
        zlevel: 2,
        effect: {
            show: true,
            period: 2,
            trailLength: 0,
            symbol: planePath,
            symbolSize: 15
        },
        lineStyle: {
            normal: {
                
                color: '#fff',//飞线底色
                width: 0,
                opacity: 0.4,
                curveness: 0.2
            }
        },
        data: convertData(item[1])
    }, 
    {
        name: item[0],
        type: 'effectScatter',
        coordinateSystem: 'geo',
        zlevel: 2,
        rippleEffect: {
            brushType: 'stroke'
        },
        label: {
            normal: {
                show: true,
                position: 'right',
                formatter: '{b}'
            }
        },
        symbolSize: function (val) {
            return val[2]/8;
        },
        itemStyle: {
            normal: {
                color: '#F6FA31'//点颜色
            }
        },
        data: item[1].map(function (dataItem) {
            return {
                name: dataItem[1].name,
                value: geoCoordMap[dataItem[1].name].concat([dataItem[1].value])
            };
        })
    }
    );
});

      let flightoption = {
        tooltip: {
            trigger: 'item'
        },
        geo: {
            map: 'china',
            zoom: 1.25,
            label: {
                emphasis: {
                    show: false
                }
            },
            roam: true,
            itemStyle: {
                normal: {
                    areaColor: '#E71626',
                    borderColor: '#FFB700'
                },
                emphasis: {
                    areaColor: '#DC3E00'
                }
            }
        },
        visualMap: {
            min: 0,
            max: 150,

            text: ['多', '少'],
            realtime: false,
            calculable: true,
            inRange: {
              color: ['lightskyblue', 'yellow', 'orangered']
            }
          },
        series: series,
        animationEasing: 'elasticOut'
      }
      return flightoption
    },
    // 初始化业务办理量echart
    initMyMapChart(currentChart) {
      // let option = this.getMyMapOption()
      let option = this.getMyMapFlightOption()
      currentChart.setOption(option)
      currentChart.on('click', function (params) {

        if(params.data.name=='南昌'){
				  location.href = 'http://36.137.58.190:52009/#/countyboard';//测试效果
			  }
170     });
    },
    // 生成飞线函数
    // convertData(data) {
    //   var res = [];
    //   for (var i = 0; i < data.length; i++) {
    //       var dataItem = data[i];
    //       var fromCoord = this.geoCoordMap[dataItem[0].name];
    //       var toCoord = this.geoCoordMap[dataItem[1].name];
    //       if (fromCoord && toCoord && dataItem[1]) {
    //           res.push([{
    //               coord: toCoord,
    //               visualMap:false,
    //               itemStyle:{
    //                   color:'#F6FA31'
    //               }
    //           }, {
    //               coord: fromCoord,
    //               visualMap:false,
    //               itemStyle:{
    //                   color:'#F6FA31'
    //               }
    //           }]);
    //       }
    //   }
    //   return res;
    // }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
body{
    line-height: 1.15;
    font-size: 0.5rem;
    margin: 0;
    padding: 0;
    background-image:url(../assets/images/background.png);
    background-position: absolute;
    background-size: cover ;
    background-attachment: fixed ;
    // background-attachment: fixed ;
    background-repeat: no-repeat;
}
@keyframes animated-border {
  0% {
    box-shadow: 0 0 0 0 rgba(255,255,255,0.4);
  }
  100% {
     box-shadow: 0 0 0 20px rgba(255,255,255,0);
  }
}
@media  screen and (max-device-height:2000px ) {
  .viewport {
    /* 限定大小 */
    width: 100%;
    height: 720px;
    margin: 0px 0px 40px 0px;
    display: flex;
    padding: 3.667rem 0.833rem 0;
  }
}
@media  screen and (max-device-height:4000x ) {
  .viewport {
    /* 限定大小 */
    width: 100%;
    height: 1080px;
    margin: 0px 0px 40px 0px;
    display: flex;
    padding: 3.667rem 0.833rem 0;
  }
}


/* 左侧数据部分 */
.left-part{
  flex: 1;
  flex-direction: column;
  display: flex;
  margin-left: 3%;
}

/* 左上角数据表部分 */
.chart1{
  background-color: rgb(255, 255, 255,0.25);
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  width: 100%;
  flex: 3;
}
.chart1:hover{
  animation: animated-border 1.5s infinite;
}

/* 数据表标题 */
.title{
  display: flex;
  margin-left: 2%;
  margin-top: 3%;
}

/* 数据表标题小图片 */
.title-img{
  flex: 1;
  height:10%;
}
.title-img img{
  height: 35px;
}

/* 数据表标题文字 */
.title-txt{
  flex: 5;
}
.title-txt h1{
  color: rgb(255, 140, 0);
}

/* 数据表标题时间 */
.title-time{
  flex: 3;
  color: white;
  font-size: 10px;
  margin-top: 2%;
  margin-left: 2%
}
.title-time p{
  font-size: 11px;
}

/* 分割线 */
.line{
  padding-left: 35px;
  margin-top: -10px;
}
.line img{
  width: 100%;
  height: 3px;
}
.line-right{
  padding-left: 35px;
  margin-top: -2px;
}
.line-right img{
  width: 100%;
  height: 3px;
}
.nav1{
  flex: 1;
}
/* 左上数据表 */
.people{
  height: 100%;
  width: 100%;
  margin-bottom: 20px;
  margin-top: -20px;
}

/* 左下数据表部分 */
.chart2{
  background-color: rgb(255, 255, 255,0.25);
  height: 28%;
  border-radius: 10px;
  margin-top: 20px;
  flex: 2;
  display: flex;
  flex-direction: column;
}
.chart2:hover{
  animation: animated-border 1.5s infinite;
}
.nav2{
  flex: 1;
  justify-content: center; /*水平居中*/
  align-items: Center;
}
/* 左下数据表 */
.numdata{
  width: 100%;
  height: 100%;
  margin-left: -10%;

}

/* 中部 */
.center-part{
  flex:1.5;
  display: flex;
  flex-direction: column;
}
.map{
  flex: 3;
  margin-bottom: 1%;
}

.map .geo {
  width: 100%;
  height: 100%;
}
.table{
  flex: 2;
}
.chart3{
  height: 100%;
  width: 100%;
}


/* 右部 */
.right-part{
  flex:1;
  margin-right:5%;
  display: flex;
  flex-direction: column;
  height: 100%;
}
.chart4{
  background-color: rgb(255, 255, 255,0.25);
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  flex: 3;
}
.chart4:hover{
  animation: animated-border 1.5s infinite;
}
.title-right{
  height: 35px;
  display: flex;
  margin-left: 8px;
  padding-top: 8px;
}

.table1 th{
  border-bottom: 1px solid rgba(255,255,255,.2);
  font-size: 13px; 
  color:rgba(255,255,255,.6); 
  font-weight: normal; 
  padding:0px 12px 0px 10px;
}
.titleline{
  height: 10%;
}

.table1 td{ 
  font-size: 16px; 
  color:rgb(255, 255, 255); 
  padding-left: 10%;
}
.table1 span{ 
  width: 24px; 
  height: 24px; 
  border-radius: 53px; 
  display: block; 
  background: #878787; 
  color: #fff; 
  line-height: 24px; 
  text-align: center;
}

.table1 tr:nth-child(2) span{ 
  background: #ed405d
}
.table1 tr:nth-child(3) span{ 
  background: #f78c44
}
.table1 tr:nth-child(4) span{ 
  background: #49bcf7
}
/* 数据表标题小图片 */
.title-img-right{
  flex: 1;
}
.title-img-right img{
  height: 35px;
}

/* 数据表标题文字 */
.title-txt-right{
  flex: 5;
}
.title-txt-right h1{
  color: rgb(255, 140, 0);
}

/* 数据表标题时间 */
.title-time-right{
  flex: 3;
  color: white;
  font-size: 10px;
  margin-top: 5px;
  margin-left: 5px;
}
.title-time-right p{
  font-size: 11px;
}
.nav{
  padding-top: 5px;
  padding-left: 15px;
}
.nav p{
  display: inline;
  font-size: 15px;
  font-family: "Microsoft YaHei";
  color: #fff;
  font-weight: bold;
}
.gover{
  margin-right: 3%;
}
.txtline{
  margin-right: 10%;
}
.nav3{
  height: 100%;
}
.total{
  height: 100%;
}
.chart5{
  background-color: rgb(255, 255, 255,0.25);
  border-radius: 10px;
  margin-top: 20px;
  flex: 2;
  display: flex;
  flex-direction: column;
}
.chart5:hover{
  animation: animated-border 1.5s infinite;
}
.chart5-part{
  display: flex;
  height: 200px;
}
.nav4{
  flex: 1;
  height:100%;
}
.contact{
  height: 100%;
}
.contact-table{
  flex: 1;
  height: 100%;
  margin-left: 5%;
}

.contact-table table{
  align-content: center;
  width: 150px;
  margin-top: 20px;
  margin-left: -20px;
  border-top-width: 2px;
  border-top-style: solid;
  border-top-color: rgb(126, 0, 0);
  border-bottom-width: 2px;
  border-bottom-style: solid;
  border-bottom-color: rgb(126, 0, 0);
}
.contact-table table th{
  padding: 5px 10px;
  font-size: 12px;
  font-family: Verdana;
  color: rgb(109, 0, 0);
}
.contact-table table tr:nth-child(even) {
  background: rgb(238, 211, 210)
}
.contact-table table tr:nth-child(odd) {
  background: #ffffff7a
}
.row1{
  font-size: 14px;
  color:#ffffff
}
.row2{
  font-size: 10px;
  color:#fff
}
.footer {
  bottom:1rem;
  z-index:1;
  position:fixed;
  width:100%;
  text-align:center;
  color: white;
  font-size: 10px;
  margin-top: -10px;
}
</style>
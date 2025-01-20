<template>
  <div class="viewport">
    <div class="toppart">
      <div class="mappart">
        <div class="btn-part">
          <button onclick="gridLoad()" class="gridload"></button>
          <button onclick="microgridLoad()" class="microgridload"></button>
          <button
            class="backhome"
            onclick="window.location.href='http://36.137.58.190:52009/#/databoard'"
          ></button>
        </div>
        <div class="map" id="map"></div>
      </div>
      <div class="area-list-part">
        <div class="title">
          <div class="title-img">
            <img src="../assets/images/titleimg.png" />
          </div>
          <div class="title-txt">
            <h1>返乡用户类型</h1>
          </div>
          <div class="title-time">
            <p>截止：{{ recordDate }}</p>
          </div>
        </div>
        <div class="line">
          <img src="../assets/images/line.png" />
        </div>
        <div
          class="right-top-chart"
          id="right-top-chart"
          ref="rightTopChart"
        ></div>
      </div>
    </div>
    <div class="bottompart">
      <div class="list1-part">
        <table id="area-score-list">
          <tr>
            <th>分组</th>
            <th colspan="4">群雄逐鹿</th>
            <th colspan="6">奋勇争先</th>
            <th colspan="2">砥砺奋进</th>
          </tr>
          <tr>
            <td>区县</td>
            <td>{{ businessCounty[0] }}</td>
            <td>{{ businessCounty[1] }}</td>
            <td>{{ businessCounty[2] }}</td>
            <td>{{ businessCounty[3] }}</td>
            <td>{{ businessCounty[4] }}</td>
            <td>{{ businessCounty[5] }}</td>
            <td>{{ businessCounty[6] }}</td>
            <td>{{ businessCounty[7] }}</td>
            <td>{{ businessCounty[8] }}</td>
            <td>{{ businessCounty[9] }}</td>
            <td>{{ businessCounty[10] }}</td>
            <td>{{ businessCounty[11] }}</td>
          </tr>
          <tr v-for="(item, index) in businessCountyScore">
            <td>{{ item.businessname }}</td>
            <td>{{ item.scoreList[0] }}</td>
            <td>{{ item.scoreList[1] }}</td>
            <td>{{ item.scoreList[2] }}</td>
            <td>{{ item.scoreList[3] }}</td>
            <td>{{ item.scoreList[4] }}</td>
            <td>{{ item.scoreList[5] }}</td>
            <td>{{ item.scoreList[6] }}</td>
            <td>{{ item.scoreList[7] }}</td>
            <td>{{ item.scoreList[8] }}</td>
            <td>{{ item.scoreList[9] }}</td>
            <td>{{ item.scoreList[10] }}</td>
            <td>{{ item.scoreList[11] }}</td>
          </tr>
        </table>
      </div>
      <div class="toplist-part">
        <div>
          <div class="title">
            <div class="title-img">
              <img src="../assets/images/titleimg.png" />
            </div>
            <div class="title-txt">
              <h1>网格龙虎榜</h1>
            </div>
            <div class="title-time">
              <p>截止：{{ recordDate }}</p>
            </div>
          </div>
          <div class="line">
            <img src="../assets/images/line.png" />
          </div>
          <div class="header">
            <div class="one"><label>排名</label></div>
            <div class="two"><label>区县</label></div>
            <div class="three"><label>网格</label></div>
            <div class="four"><label>得分</label></div>
          </div>
        </div>
        <div class="centerlist">
          <tr v-for="(item, index) in centerRankInfos">
            <td class="one_num">
              <span>{{ item.rank }}</span>
            </td>
            <td class="two_text">{{ item.countyName }}</td>
            <td class="three_text">{{ item.centerName }}<br /></td>
            <td class="four_text">{{ item.score }}<br /></td>
          </tr>
        </div>
      </div>
    </div>
    <footer>
      <p>业务指导：南昌移动市场部 技术支持：南昌移动IT科创团队</p>
    </footer>
  </div>
</template>

<script>
import * as echarts from "echarts";
// import {tianjingmapgl} from '@tianditu/webgl'
import { getCountyHomeData } from "@/api/springmarketing/returndetail";
// import "@/api/databoard/tianjingmap-gl.js"
// import "@/api/databoard/nanchang.js"
// import "@/api/databoard/center2.js"
export default {
  name: "Countyboard",
  data() {
    return {
      recordDate: "2023-12-22",
      centerRankInfos: [],
      countyList: [
        {
          countyId: "1",
          countyName: "东湖区",
          returnPeople: 368408,
          contactPeople: 254210,
          conversionRate: 0.21,
          sort: 1,
        },
        {
          countyId: "2",
          countyName: "西湖区",
          returnPeople: 0,
          contactPeople: 0,
          conversionRate: 0.0,
          sort: 2,
        },
        {
          countyId: "3",
          countyName: "青山湖区",
          returnPeople: 0,
          contactPeople: 0,
          conversionRate: 0.0,
          sort: 3,
        },
        {
          countyId: "4",
          countyName: "青云谱区",
          returnPeople: 0,
          contactPeople: 0,
          conversionRate: 0.0,
          sort: 4,
        },
        {
          countyId: "5",
          countyName: "昌北区",
          returnPeople: 0,
          contactPeople: 0,
          conversionRate: 0.0,
          sort: 5,
        },
        {
          countyId: "6",
          countyName: "红谷滩区",
          returnPeople: 0,
          contactPeople: 0,
          conversionRate: 0.0,
          sort: 6,
        },
        {
          countyId: "7",
          countyName: "高新区",
          returnPeople: 0,
          contactPeople: 0,
          conversionRate: 0.0,
          sort: 7,
        },
        {
          countyId: "8",
          countyName: "湾里区",
          returnPeople: 0,
          contactPeople: 0,
          conversionRate: 0.0,
          sort: 8,
        },
        {
          countyId: "9",
          countyName: "南昌县",
          returnPeople: 0,
          contactPeople: 0,
          conversionRate: 0.0,
          sort: 9,
        },
        {
          countyId: "10",
          countyName: "新建区",
          returnPeople: 0,
          contactPeople: 0,
          conversionRate: 0.0,
          sort: 10,
        },
        {
          countyId: "11",
          countyName: "进贤县",
          returnPeople: 0,
          contactPeople: 0,
          conversionRate: 0.0,
          sort: 11,
        },
        {
          countyId: "12",
          countyName: "安义县",
          returnPeople: 0,
          contactPeople: 0,
          conversionRate: 0.0,
          sort: 12,
        },
      ],
      businessMap: {
        群雄逐鹿: [
          {
            countyId: "1",
            countyName: "东湖区",
            rankArray: [2, 1, 1, 1],
            scorearray: [180, 216, 398, 474],
          },
          {
            countyId: "2",
            countyName: "西湖区",
            rankArray: [2, 2, 2, 2],
            scorearray: [180, 0, 0, 0],
          },
          {
            countyId: "6",
            countyName: "红谷滩区",
            rankArray: [4, 2, 2, 2],
            scorearray: [0, 0, 0, 0],
          },
          {
            countyId: "10",
            countyName: "新建区",
            rankArray: [4, 2, 2, 2],
            scorearray: [0, 0, 0, 0],
          },
        ],
        砥砺奋进: [
          {
            countyId: "5",
            countyName: "昌北区",
            rankArray: [4, 2, 2, 2],
            scorearray: [0, 0, 0, 0],
          },
          {
            countyId: "11",
            countyName: "进贤县",
            rankArray: [4, 2, 2, 2],
            scorearray: [0, 0, 0, 0],
          },
        ],
        奋勇争先: [
          {
            countyId: "3",
            countyName: "青山湖区",
            rankArray: [4, 2, 2, 2],
            scorearray: [0, 0, 0, 0],
          },
          {
            countyId: "4",
            countyName: "青云谱区",
            rankArray: [4, 2, 2, 2],
            scorearray: [0, 0, 0, 0],
          },
          {
            countyId: "7",
            countyName: "高新区",
            rankArray: [4, 2, 2, 2],
            scorearray: [0, 0, 0, 0],
          },
          {
            countyId: "8",
            countyName: "湾里区",
            rankArray: [4, 2, 2, 2],
            scorearray: [0, 0, 0, 0],
          },
          {
            countyId: "9",
            countyName: "南昌县",
            rankArray: [4, 2, 2, 2],
            scorearray: [0, 0, 0, 0],
          },
          {
            countyId: "12",
            countyName: "安义县",
            rankArray: [1, 2, 2, 2],
            scorearray: [190, 0, 0, 0],
          },
        ],
      },
      businessCounty: [
        "东湖区",
        "西湖区",
        "红谷滩区",
        "新建区",
        "青山湖区",
        "青云谱区",
        "高新区",
        "湾里区",
        "南昌县",
        "安义县",
        "昌北区",
        "进贤县",
      ],
      businessCountyScore: [
        {
          businessname: "等效",
          scoreList: [
            10.2, 9.7, 7.9, 7.9, 7.9, 7.9, 7.9, 7.9, 7.9, 7.9, 7.9, 7.9,
          ],
          rankList: [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
        },
        {
          businessname: "等效带宽",
          scoreList: [
            10.2, 9.7, 7.9, 7.9, 7.9, 7.9, 7.9, 7.9, 7.9, 7.9, 7.9, 7.9,
          ],
          rankList: [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
        },
        {
          businessname: "5G合约",
          scoreList: [
            10.2, 9.7, 7.9, 7.9, 7.9, 7.9, 7.9, 7.9, 7.9, 7.9, 7.9, 7.9,
          ],
          rankList: [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
        },
        {
          businessname: "CHN产品",
          scoreList: [
            10.2, 9.7, 7.9, 7.9, 7.9, 7.9, 7.9, 7.9, 7.9, 7.9, 7.9, 7.9,
          ],
          rankList: [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
        },
      ],
    };
  },
  beforeCreated() {},
  created() {
    this.getList();
    // console.log("created"+this.currentYearData)
  },
  mounted() {
    this.initTdtMap();
    this.peopleChart = echarts.init(this.$refs.rightTopChart);
    this.initPeopleChart(this.peopleChart);
  },
  watch: {
    // 监听currentYearData变化
    countyList() {
      // 从新渲染ECharts图表
      console.log("enter countyList");
      let option = this.getPeopleChartOption();
      this.peopleChart.setOption(option);
    },
  },
  methods: {
    // 获取
    initTdtMap() {
      // let t = window.T
      // console.log(JSON.stringify(window))
      // this.tdtMap
      const key = "55be995c6540470c9fb4816ea5aae9d5";
      let hoveredPolygonId = null;
      /**
       * 初始化地图实例
       */
      const map = new tianjingmapgl.Map({
        container: "map", // container ID
        style: `http://123.14.199.58:8086/tianjing-server/mapdata-api/services/MapStyleServer/style/a331756fb56441a0b14f740b942edbaa/styleJSON?key=${key}`, // style URL
        center: [115.844372, 28.661611], // starting position [lng, lat]
        zoom: 9, // starting zoom
        pitch: 0,
      });
    },

    // 获取接口请求数据
    getList() {
      getCountyHomeData().then((response) => {
        this.recordDate = response.recordDate;
        this.centerRankInfos = response.centerRankInfos;
        this.countyList = response.countyList;
        this.businessCounty = response.countyBusinessTableVo.businessCounty;
        this.businessCountyScore =
          response.countyBusinessTableVo.businessCountyScore;
      });
    },
    // 获取返乡人数变化option
    getPeopleChartOption() {
      let option;
      let contact = Array.from(this.countyList, function (item) {
        return item.contactPeople;
      });
      let back = Array.from(this.countyList, function (item) {
        return item.returnPeople;
      });
      let countyName = Array.from(this.countyList, function (item) {
        return item.countyName;
      });
      let noncontact = [];
      let rate = Array.from(this.countyList, function (item) {
        return item.conversionRate;
      });
      for (let i = 0; i < contact.length; i++) {
        let temp = back[i] - contact[i];
        // let temp2 = contact[i] / back[i];
        noncontact.push(temp);
        // rate.push(temp2);
      }
      option = {
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross",
            crossStyle: {
              color: "#999",
            },
          },
        },
        grid: {
          bottom: 20,
          right: 40,
        },
        toolbox: {
          feature: {
            dataView: { show: true, readOnly: false },
            magicType: { show: true, type: ["line", "bar"] },
            restore: { show: true },
            saveAsImage: { show: true },
          },
          iconStyle: {
            borderColor: "#ffffff",
          },
        },
        legend: {
          data: ["接触数", "未接触数", "转化率"],
          textStyle: {
            color: "#fff",
          },
        },
        xAxis: [
          {
            type: "category",
            data: countyName,
            axisPointer: {
              type: "shadow",
            },
            axisLabel: {
              show: true,
              interval: 0,
              color: "white",
            },
            axisLine: {
              lineStyle: {
                color: "#ffffff",
                width: 1,
              },
            },
          },
        ],
        yAxis: [
          {
            type: "value",
            name: "人数",
            color: "white",
            axisLabel: {
              formatter: "{value} 人",
              color: "white",
            },
            axisLine: {
              lineStyle: {
                color: "#ffffff",
                width: 1,
              },
            },
          },
          {
            type: "value",
            name: "转化率",
            min: 0,
            max: 1,
            nameTextStyle: {
              color: "white",
            },
            axisLabel: {
              formatter: "{value}",
              color: "white",
            },
            axisLine: {
              color: "white",
            },
          },
        ],
        series: [
          {
            name: "接触数",
            type: "bar",
            stack: "返乡",
            itemStyle: {
              color: "#FF9100",
            },
            label: {
              show: true,
              position: "inside",
              color: "#FFEE00",
            },
            tooltip: {
              valueFormatter: function (value) {
                return value + " 人";
              },
            },
            data: contact,
          },
          {
            name: "未接触数",
            type: "bar",
            stack: "返乡",
            itemStyle: {
              color: "#FFEE00",
            },
            label: {
              show: true,
              position: "inside",
              color: "#FF9100",
            },
            tooltip: {
              valueFormatter: function (value) {
                return value + " 人";
              },
            },
            data: noncontact,
          },
          {
            name: "转化率",
            type: "line",
            yAxisIndex: 1,
            itemStyle: {
              color: "#9a0000",
            },
            tooltip: {
              valueFormatter: function (value) {
                return value.toFixed(4) * 100 + "%";
              },
            },
            data: rate,
          },
        ],
      };
      return option;
    },
    // 初始化返乡人数变化option
    initPeopleChart(currentChart) {
      let option = this.getPeopleChartOption();
      currentChart.setOption(option);
    },
  },
};
</script>

<style rel="stylesheet/scss" lang="scss">
body {
  line-height: 1.15;
  font-size: 0.5rem;
  height: 100%;
  margin: 0;
  padding: 0;
  background-image: url(../assets/images/background.png);
  background-position: absolute;
  background-repeat: no-repeat;
  background-size: cover;
  background-attachment: fixed;
}
@keyframes animated-border {
  0% {
    box-shadow: 0 0 0 0 rgba(255, 255, 255, 0.4);
  }
  100% {
    box-shadow: 0 0 0 20px rgba(255, 255, 255, 0);
  }
}
@media screen and (max-device-height: 2000px) {
  .viewport {
    /* 限定大小 */
    width: 100%;
    height: 720px;
    margin: 0px 0px 40px 0px;
    display: flex;
    padding: 3.667rem 0.833rem 0;
    flex-direction: column;
  }
}
@media screen and (max-device-height: 4000x) {
  .viewport {
    /* 限定大小 */
    width: 100%;
    height: 1080px;
    margin: 0px 0px 40px 0px;
    display: flex;
    padding: 3.667rem 0.833rem 0;
    flex-direction: column;
  }
}
footer {
  text-align: center;
  color: white;
  font-size: 10px;
  margin-top: -10px;
}
.toppart {
  flex: 3;
  display: flex;
}
.bottompart {
  flex: 1;
  display: flex;
}
.btn-part {
  position: absolute;
  z-index: 10;
  display: flex;
  flex-direction: column;
  margin-left: 10px;
  margin-top: 10px;
}
.gridload {
  background: url(../assets/images/home.png) no-repeat 0 0 / contain;
  height: 30px;
  width: 30px;
  border: none;
  margin-bottom: 10px;
}
.gridload:hover {
  background: url(../assets/images/home2.png) no-repeat 0 0 / contain;
}
.microgridload:hover {
  background: url(../assets/images/flag2.png) no-repeat 0 0 / contain;
}
.backhome {
  background: url(../assets/images/homepage.png) no-repeat 0 0 / contain;
  height: 30px;
  width: 30px;
  border: none;
  margin-bottom: 10px;
}
.backhome:hover {
  background: url(../assets/images/backhome2.png) no-repeat 0 0 / contain;
  height: 30px;
  width: 30px;
  border: none;
}
.microgridload {
  background: url(../assets/images/area.png) no-repeat 0 0 / contain;
  height: 30px;
  width: 30px;
  border: none;
}
.mappart {
  padding: 1% 3%;
  width: 40%;
  height: 90%;
}
.map {
  height: 100%;
  width: 100%;
  background-color: aliceblue;
}
.map:hover {
  animation: animated-border 1.5s infinite;
}
.area-list-part {
  height: 90%;
  width: 58%;
  background-color: rgb(255, 255, 255, 0.25);
  margin-top: 1%;
}
.right-top-chart {
  height: 80%;
  width: 100%;
}
.area-list-part:hover {
  animation: animated-border 1.5s infinite;
}
.list1-part {
  margin-left: 3%;
}
#area-score-list {
  font-family: "微软雅黑";
  font-size: 10px;
  text-align: center;
  border-collapse: collapse;
  width: 100%;
  height: 100%;
}

#area-score-list td,
#area-score-list th {
  border: 2px solid #ddd;
  padding: 8px;
}

#area-score-list tr:nth-child(even) {
  background-color: #ff9100;
}
#area-score-list tr:nth-child(even):hover {
  background-color: #ddd;
}
#area-score-list tr:nth-child(odd) {
  background-color: #ffb85b;
}
#area-score-list tr:nth-child(odd):hover {
  background-color: #ddd;
}
#area-score-list th {
  padding-top: 2%;
  padding-bottom: 2%;
  background-color: #8d0000;
  color: rgb(255, 255, 255);
}
.header {
  height: 40px;
  font-size: 15px;
  color: #fff;
}

.header .one {
  padding-top: 8px;
  float: left;
  text-align: center;
  margin-left: 20px;
}
.header .two {
  padding-top: 8px;
  float: left;
  text-align: center;
  margin-left: 40px;
}
.header .three {
  padding-top: 8px;
  float: left;
  text-align: center;
  margin-left: 100px;
}
.header .four {
  padding-top: 8px;
  float: left;
  text-align: center;
  margin-left: 100px;
  margin-right: 30px;
}
.one_num {
  float: left;
  text-align: center;
  background-color: #600101;
  border-radius: 10px;
  -webkit-box-flex: 0.7;
  -ms-flex: 0.7;
  flex: 0.35;
  margin-left: 26px;
}

.centerlist {
  height: 40px;
  font-size: 15px;
  color: #fff;
}
.centerlist tr {
  display: flex;
  margin-bottom: 10px;
  height: 20px;
}

.centerlist .one {
  float: left;
  text-align: center;
  font-size: 15px;
  color: #fff;
}

.centerlist .two_text {
  float: inline-start;
  text-align: center;
  font-size: 15px;
  color: #fff;
  flex: 2;
  margin-left: 15px;
}
.centerlist .three_text {
  float: inline-start;
  text-align: center;
  font-size: 15px;
  color: #fff;
  flex: 3;
  margin-left: 10px;
}
.centerlist .four_text {
  float: left;
  text-align: center;
  font-size: 15px;
  color: #fff;
  flex: 2;
}

#tab {
  height: 100px;
  overflow: hidden;
}
.toplist-part {
  background-color: rgb(255, 255, 255, 0.25);
  margin-left: 5%;
}
.toplist-part:hover {
  animation: animated-border 1.5s infinite;
}
/* 数据表标题 */
.title {
  display: flex;
  margin-left: 2%;
  margin-top: 1%;
}

/* 数据表标题小图片 */
.title-img {
  flex: 1;
  height: 10%;
}
.title-img img {
  height: 35px;
}

/* 数据表标题文字 */
.title-txt {
  flex: 5;
}
.title-txt h1 {
  color: rgb(255, 140, 0);
}

/* 数据表标题时间 */
.title-time {
  flex: 3;
  color: white;
  font-size: 10px;
  margin-top: 2%;
  margin-left: 2%;
}
.title-time p {
  font-size: 11px;
}

/* 分割线 */
.line {
  padding-left: 35px;
  margin-top: -10px;
}
.line img {
  width: 100%;
  height: 3px;
}
.line-right {
  padding-left: 35px;
  margin-top: -2px;
}
.line-right img {
  width: 100%;
  height: 3px;
}
.nav1 {
  flex: 1;
}
.footer {
  bottom: 1rem;
  z-index: 1;
  position: fixed;
  width: 100%;
  text-align: center;
  color: white;
  font-size: 10px;
  margin-top: -10px;
}
</style>

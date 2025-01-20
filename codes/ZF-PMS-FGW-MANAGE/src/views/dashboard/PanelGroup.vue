<template>
  <div class="panel-group">
    <div class="card-panel-col">
        <div class="card-panel" style="width: 70%;float: left;">
          <div class="card-text-18 icon-people-color">
            2024年度
          </div>
          <div class="card-panel-description" style="padding-bottom: 18px;">
            全区情况
          </div>
        </div>
        <img src="../img/next.png" style="float: right;width:27px;padding: 31px 0px;"/>
    </div>
    <div class="card-panel-col">
      <div class="card-panel">
        <div class="icon-img">
          <img src="../img/total.png" />
        </div>
        <div class="card-panel-description">
          <div class="card-text-14 icon-people-color">项目总数</div>
          <count-to :start-val="0" :end-val="option.zmzs" :duration="3200" class="card-text-20" />
        </div>
      </div>
    </div>
    <div class="card-panel-col">
      <div class="card-panel">
        <div class="icon-img">
          <img src="../img/invest.png"/>
        </div>
        <div class="card-panel-description">
          <div class="card-text-14 icon-people-color">计划投资额</div>
          <count-to :start-val="0" :end-val="option.jhtze || 0" :duration="3200" class="card-text-20" />
          <span class="spanClass">万元</span>
        </div>
        <div style="clear: both;"></div>
      </div>
    </div>
    <div class="card-panel-col">
      <div class="card-panel">
        <div class="icon-img">
          <img src="../img/invest2.png"/>
        </div>
        <div class="card-panel-description">
          <div class="card-text-14 icon-people-color">完成投资额</div>
          <count-to :start-val="0" :end-val="option.wctze  || 0" :duration="3200" class="card-text-20" />
          <span class="spanClass">万元</span>
        </div>
        <div style="clear: both;"></div>
      </div>
    </div>
    <div style="clear: both;"></div>
  </div>
</template>

<script>
import CountTo from 'vue-count-to'
import {ndqqqk} from  "@/api/system/echarts/echarts"

export default {
  components: {
    CountTo
  },
  data(){
    return{
      option:{}
    }
  },
  mounted() {
    ndqqqk().then(res =>{
      this.option = res.data[0]
      console.log('顶部数据修改',res)
    })
  },
  methods: {
    handleSetLineChartData(type) {
      this.$emit('handleSetLineChartData', type)
    }
  }
}
</script>

<style lang="scss" scoped>
.panel-group {
  width: 100%;
  margin: 0 !important;
  .card-panel-col {
    padding:0 1%;
    width: 25% !important;
    float: left;
    margin-bottom: 32px;
  }

  .card-panel {
    background: url("../img/boxTop.png") no-repeat;
    background-size: 100% 100%;
    text-align: center;
    height: 87px;
  }
  .card-text-18{
    font-size: calc(18px + 0.1%);
    font-size: clamp(18px, calc(18px + 0.1%),26px);
    font-size: 18px;
    line-height: 24px;
    font-weight: 700;
  }
  .card-text-20{
    font-size: calc(20px + 0.1%);
    font-size: clamp(20px, calc(20px + 0.1%), 28px);
    font-weight: bold;
  }
  .icon-people-color{
    padding: 18px 0 6px;
    color: #409EFF;
  }
  .icon-people-fff{
    color: #ffffff;
  }
  .icon-img{
    width: 38%;
    float: left;
    margin: 7px 0px 7px 12px
  }
  .icon-img img{
   width: 100%;
  }
  .card-text-14{
    font-size: calc(14px + 0.1%);
    font-size: clamp(14px, calc(14px + 0.1%), 22px);
    line-height: 24px;
    font-weight: 700;
  }


    .icon-people {
      color: #40c9c6;
    }

    .icon-message {
      color: #36a3f7;
    }

    .icon-money {
      color: #f4516c;
    }

    .icon-shopping {
      color: #34bfa3
    }

    .card-panel-icon-wrapper {
      float: left;
      margin: 14px 0 0 14px;
      padding: 16px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }

    .card-panel-icon {
      float: left;
      font-size: 48px;
    }

    .card-panel-description {
      font-weight: bold;
      margin-left: 0px;
      .spanClass{
        font-size: 12px;
        color: #999999;
      }
    }

  }

@media (max-width:550px) {
  .card-panel-description {
    display: none;
  }

  .card-panel-icon-wrapper {
    float: none !important;
    width: 100%;
    height: 100%;
    margin: 0 !important;

    .svg-icon {
      display: block;
      margin: 14px auto !important;
      float: none !important;
    }
  }
}
</style>

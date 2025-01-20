<template>
  <div class="app-container home">
    <IFRAME
      v-if="showOperate"
      src="https://wx.liushaotou.ltd:20008/#/delink?link=Wt4WI1UrG1f9ZRp6yaL7crbzufcrfrOeL%2FTgxKxV1BfNeqWvi5AgOX9KgyLs6wa05%2BhrcWA8BDcDl5Z1bJ4DUw&user=C93vMgwJGFexODRj6998yd%2FgkL1mFbarW9yabu%2FN%2F5iPJ6KyWUnsEqAJguntxnpRiyoxC7dUihyZ2H45jWlSmA"
      frameBorder="0"
      marginwidth="0"
      marginheight="0"
      scrolling="no"
      style="width: 100%; height: 720px"
      ALLOWTRANSPARENCY="true"
    ></IFRAME>

    <el-row :gutter="10">
      <el-col>
        <div class="grid-content bg-purple" style="min-height: 100px">
          <div class="title">我的工作</div>

          <div class="work_section">
            <div class="work_tab">
              <div>
                <img src="../assets/icons/one.png" alt="" class="icon" />
              </div>
              <div>项目报送 {{ num1 }} 个</div>
            </div>
            <div class="work_tab">
              <div>
                <img src="../assets/icons/one.png" alt="" class="icon" />
              </div>
              <div>项目调度 {{ num2 }} 个</div>
            </div>
            <div class="work_tab">
              <div>
                <img src="../assets/icons/one.png" alt="" class="icon" />
              </div>
              <div>全链条填报 {{ num3 }} 个</div>
            </div>
            <div class="work_tab">
              <div>
                <img src="../assets/icons/one.png" alt="" class="icon" />
              </div>
              <div>项目完成 {{ num4 }} 个</div>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row :gutter="10">
      <el-col :span="12">
        <div class="grid-content bg-purple" style="height: 250px">
          <div
            style="padding-left: 10px; padding-top: 6px; padding-bottom: 1px"
          >
            <span>我的待办</span>
            <!--            <el-badge :value="worksNum" :max="99" style="padding-left: 4px; padding-bottom: 8px;">-->
            <!--            </el-badge>-->
          </div>
          <div class="line"></div>

          <div style="margin: 2px; height: 207px; overflow: auto">
            <div
              style="text-align: center; line-height: 207px; font-size: 12px"
            >
              暂无数据~
            </div>
            <!--            <ul>-->
            <!--              <li v-for="item in worksData">-->
            <!--                <div style="display: flex; align-items: center;">-->
            <!--                  <div>-->
            <!--                    <img class="remind_icon" src="../assets/icons/remind.png" alt="">-->
            <!--                  </div>-->

            <!--                  <div style="width: 98%;">-->
            <!--                    <div style="display: flex;align-items: center;width: 98%;">-->
            <!--                      <div>{{ item.type }}</div>-->
            <!--                      <div style="flex:1"></div>-->
            <!--                      <div style="right: 0px;">{{ item.time }}</div>-->
            <!--                    </div>-->
            <!--                    &lt;!&ndash; 空格 &ndash;&gt;-->
            <!--                    <div style="height: 5px;"></div>-->
            <!--                    <div>{{ item.name }}</div>-->
            <!--                  </div>-->
            <!--                </div>-->
            <!--              </li>-->
            <!--            </ul>-->
          </div>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="grid-content bg-purple" style="height: 250px">
          <div class="title">项目进程({{ index_pro }}/{{ projects }})</div>
          <div class="line"></div>
          <div>
            <el-carousel
              trigger="click"
              height="209px"
              :autoplay="false"
              @change="changePro"
            >
              <el-carousel-item
                v-for="(item, index) in projectsData"
                :key="index"
              >
                <div style="text-align: center; padding: 10px">
                  {{ item.projectName }}
                </div>
                <div>
                  <el-steps
                    :active="item.projectProgress"
                    align-center
                    finish-status="success"
                  >
                    <el-step title="谋划阶段"></el-step>
                    <el-step title="前期工作"></el-step>
                    <el-step title="开工建设"></el-step>
                    <el-step title="竣工完结"></el-step>
                  </el-steps>
                </div>
                <div style="margin: 10px">
                  <el-descriptions
                    class="margin-top"
                    :column="2"
                    size="mini"
                    :border="true"
                  >
                    <el-descriptions-item>
                      <template slot="label">
                        <i class="el-icon-user"></i>
                        项目业主
                      </template>
                      {{ item.contactsName }}
                    </el-descriptions-item>
                    <el-descriptions-item>
                      <template slot="label">
                        <i class="el-icon-mobile-phone"></i>
                        手机号
                      </template>
                      {{ item.contactsPhone }}
                    </el-descriptions-item>
                    <el-descriptions-item>
                      <template slot="label">
                        <i class="el-icon-location-outline"></i>
                        所属行业
                      </template>
                      <!-- <el-tag size="small">建筑业</el-tag> -->
                      住宿和餐饮业
                    </el-descriptions-item>
                    <el-descriptions-item>
                      <template slot="label">
                        <i class="el-icon-tickets"></i>
                        申报日期
                      </template>
                      {{ item.recordTime }}
                    </el-descriptions-item>
                  </el-descriptions>
                </div>
              </el-carousel-item>
            </el-carousel>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row :gutter="10">
      <el-col :span="12">
        <div class="grid-content bg-purple" style="height: 200px">
          <div class="title">系统公告</div>
          <div class="line"></div>
          <div style="margin: 2px; height: 157px; overflow: auto">
            <div
              style="text-align: center; line-height: 157px; font-size: 12px"
            >
              暂无数据~
            </div>
            <!--            <ul>-->
            <!--              <li v-for="item in noticesData">-->
            <!--                <div style="display: flex; align-items: center;">-->
            <!--                  <div>-->
            <!--                    <el-tag style="margin: 3px;" size="mini" :type="item.type">{{ item.name }}</el-tag>-->
            <!--                  </div>-->

            <!--                  <div style="width: 98%;">-->
            <!--                    <div style="display: flex;align-items: center;width: 98%;">-->
            <!--                      <div>{{ item.section }}</div>-->
            <!--                      <div style="flex:1"></div>-->
            <!--                      <div style="right: 0px;">{{ item.time }}</div>-->
            <!--                    </div>-->

            <!--                  </div>-->
            <!--                </div>-->
            <!--              </li>-->
            <!--            </ul>-->
          </div>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="grid-content bg-purple" style="height: 200px">
          <div class="title">登录信息</div>
          <div class="line"></div>
          <div style="margin: 10px">
            <el-descriptions class="margin-top" :column="3">
              <el-descriptions-item label="用户名">admin</el-descriptions-item>
              <el-descriptions-item label="手机号"
                >15888888888</el-descriptions-item
              >
              <el-descriptions-item label="所属部门"
                >局领导/管理员</el-descriptions-item
              >
              <el-descriptions-item label="备注">
                <el-tag size="small">企业</el-tag>
              </el-descriptions-item>
              <el-descriptions-item label="创建日期"
                >2023-12-20 14:41:41</el-descriptions-item
              >
            </el-descriptions>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { getNums, getProjectsInfo } from "@/api/index";

export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "3.8.7",
      showOperate: false,
      // 我的工作
      num1: 0,
      num2: 0,
      num3: 0,
      num4: 0,

      worksData: [
        {
          type: "项目报送",
          name: "项目名称1xxxxxxxxxxxxxxxx",
          time: "2024-05-21",
        },
        {
          type: "项目报送",
          name: "项目名称1xxxxxxxxxxxxxxxx",
          time: "2024-05-01",
        },
        {
          type: "项目调度",
          name: "项目名称1xxxxxxxxxxxxxxxx",
          time: "2024-05-21",
        },
        {
          type: "项目报送",
          name: "项目名称1xxxxxxxxxxxxxxxx",
          time: "2024-05-21",
        },
        {
          type: "项目报送",
          name: "项目名称1xxxxxxxxxxxxxxxx",
          time: "2024-03-21",
        },
        {
          type: "项目申请",
          name: "项目名称1xxxxxxxxxxxxxxxx",
          time: "2024-05-21",
        },
        {
          type: "项目报送",
          name: "项目名称1xxxxxxxxxxxxxxxx",
          time: "2024-05-21",
        },
        {
          type: "项目报送",
          name: "项目名称1xxxxxxxxxxxxxxxx",
          time: "2024-02-21",
        },
      ],
      index_pro: 1,
      projects: 10,
      projectsData: [],
      noticesData: [
        {
          section: "发改委项目管理平台初版上线！",
          type: "danger",
          name: "喜报",
          time: "2024-5-25",
        },
        {
          section: "项目进程展板内容更新",
          type: "success",
          name: "更新",
          time: "2024-5-25",
        },
        {
          section: "请各单位同事积极使用",
          type: "",
          name: "公文",
          time: "2024-5-25",
        },
        {
          section: "发改委项目管理平台初版上线！",
          type: "",
          name: "公文",
          time: "2024-5-25",
        },
        {
          section: "明天下午2点系统将进行更新优化",
          type: "warning",
          name: "通告",
          time: "2024-5-25",
        },
        {
          section: "项目进程展板内容更新",
          type: "success",
          name: "更新",
          time: "2024-5-25",
        },
        {
          section: "项目进程展板内容更新",
          type: "success",
          name: "更新",
          time: "2024-5-25",
        },
      ],
    };
  },
  computed: {
    worksNum: {
      get() {
        return this.worksData.length;
      },
    },
  },
  created() {
    this.getNums();
    this.getProjectsInfo();
  },
  beforeUpdate() {},
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    },
    // 获取工作台数字
    getNums() {
      getNums()
        .then((res) => {
          console.log(res);
          this.num1 = res.data.projectSubmission;
          this.num2 = res.data.projectScheduling;
          this.num3 = res.data.projectCompletion;
          this.num4 = res.data.fullLink;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getProjectsInfo() {
      getProjectsInfo(1, 10)
        .then((res) => {
          let array = res.rows;
          this.projects = array.length;
          console.log(res);

          for (let i = 0; i < array.length; i++) {
            const element = {
              projectName: array[i].projectName,
              projectProgress: array[i].projectProgress,
              contactsName: array[i].contactsName,
              contactsPhone: array[i].contactsPhone,
              recordTime: array[i].recordTime,
            };

            this.projectsData.push(element);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 改变项目预览
    changePro(now, prev) {
      // if (prev > now) {
      //   this.index_pro++
      // } else {
      //   this.index_pro--
      // }
      console.log("图片索引切换: " + prev + "--->" + now);

      this.index_pro = now + 1;
    },
  },
};
</script>

<style scoped lang="scss">
// .el-carousel__item:nth-child(2n) {
//   background-color: #99a9bf;
// }

// .el-carousel__item:nth-child(2n+1) {
//   background-color: #d3dce6;
// }

.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }

  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }

  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }

  .el-row {
    margin-bottom: 10px;

    &:last-child {
      margin-bottom: 0;
    }
  }

  .el-col {
    border-radius: 4px;
  }

  .bg-purple {
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  }

  .grid-content {
    border-radius: 4px;
  }

  .title {
    padding: 10px;
  }

  .work_section {
    display: flex;
    align-items: center;
    margin-left: 10px;
  }

  .work_tab {
    min-width: 180px;
    min-height: 50px;
    margin-right: 30px;
    display: flex;
    align-items: center;
    background-color: #a3b7fa;
    border-radius: 4px;
    color: #ffffff;
    box-shadow: 3px 3px 3px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  }

  .icon {
    width: 35px;
    height: 35px;
    margin-top: 3px;
    margin-left: 20px;
    margin-right: 15px;
    border: solid 4px #c2d0ff;
    border-radius: 50%;
    background-color: #ffffff;
  }

  .remind_icon {
    width: 40px;
    height: 40px;
    margin: 10px;
    border: solid 3px #ffa621;
    border-radius: 4px;
    background-color: #ffffff;
  }

  .line {
    margin-left: 4px;
    margin-right: 4px;
    border-width: 1px 0px 0px 0px;
    border-style: solid;
    border-color: #e1e1e1;
  }

  .margin-top {
    line-height: 10px;
    font-size: 12px;
  }
}
</style>

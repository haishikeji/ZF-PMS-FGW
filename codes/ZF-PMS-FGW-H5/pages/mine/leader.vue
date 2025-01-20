<template>
  <view class="mine-container" :style="{height: `${windowHeight}px`}">
    <!--顶部个人信息栏-->
    <view class="header-section">
      <view class="flex padding justify-between">
        <view class="flex align-center">
          <view v-if="!avatar" class="cu-avatar xl round bg-white">
            <view class="iconfont icon-people text-gray icon"></view>
          </view>
         <!-- <image v-if="avatar" @click="handleToAvatar" :src="avatar" class="cu-avatar xl round" mode="widthFix">
          </image> -->
          <view v-if="!name" @click="handleToLogin" class="login-tip">
            点击登录
          </view>
          <view class="user-info">
						<view>
							<image class="a_image" src="/static/avatar.png" mode="widthFix"></image>
						</view>
						
            <view class="u_title">
							<view>
								<text class="text">{{nickname}}</text>
								<text class="text">{{number}}</text>
							
							</view>
              <view>
								<text class="text">{{affiliation}}</text>
							</view>
							
            </view>
          </view>
        </view>
        <view @click="handleToInfo" class="flex align-center">
          <text>个人信息</text>
          <view class="iconfont icon-right"></view>
        </view>
      </view>
    </view>

    <view class="content-section" >
      <view class="mine-actions grid col-4 text-center" v-show="false">
        <view class="action-item" @click="handleJiaoLiuQun">
          <view class="iconfont icon-friendfill text-pink icon"></view>
          <text class="text">交流群</text>
        </view>
        <view class="action-item" @click="handleBuilding">
          <view class="iconfont icon-service text-blue icon"></view>
          <text class="text">在线客服</text>
        </view>
        <view class="action-item" @click="handleBuilding">
          <view class="iconfont icon-community text-mauve icon"></view>
          <text class="text">反馈社区</text>
        </view>
        <view class="action-item" @click="handleBuilding">
          <view class="iconfont icon-dianzan text-green icon"></view>
          <text class="text">点赞我们</text>
        </view>
      </view>

      <view class="menu-list">
        <view class="list-cell list-cell-arrow" @click="handleToEditInfo">
          <view class="menu-item-box">
            <view class="iconfont icon-user menu-icon"></view>
            <view>编辑资料</view>
          </view>
        </view>
        <view class="list-cell list-cell-arrow" @click="handleHelp">
          <view class="menu-item-box">
            <view class="iconfont icon-help menu-icon"></view>
            <view>常见问题</view>
          </view>
        </view>
        <view class="list-cell list-cell-arrow" @click="handleAbout">
          <view class="menu-item-box">
            <view class="iconfont icon-aixin menu-icon"></view>
            <view>关于我们</view>
          </view>
        </view>
        <view class="list-cell list-cell-arrow" @click="handleToSetting">
          <view class="menu-item-box">
            <view class="iconfont icon-setting menu-icon"></view>
            <view>应用设置</view>
          </view>
        </view>
      </view>

    </view>
	
	<uv-tabbar :value="value" :fixed="true" @change="changePage">
		<uv-tabbar-item text="首页" icon="home"></uv-tabbar-item>
		<uv-tabbar-item text="工作台" icon="grid-fill"></uv-tabbar-item>
		<uv-tabbar-item text="我的" icon="account"></uv-tabbar-item>
	</uv-tabbar>
  </view>
</template>

<script>
  import storage from '@/utils/storage'
  
  export default {
    data() {
		return {
			value: 2,
			// avatar:'http://36.137.58.190:52008/spring_marketing/profile/upload/2024/02/26/tx_20240226202632A002.jpg',
			nickname: this.$store.state.user.nickname,
			number: this.$store.state.user.name,
			name: this.$store.state.user.name,
			affiliation: this.$store.state.user.affiliation,
			version: getApp().globalData.config.appInfo.version
		}
    },
		onLoad() {
			console.log('name:',this.$store.state.user.name)
			console.log('avatar:',this.$store.state.user.avatar)
			console.log('roles:',this.$store.state.user.roles)
			console.log('permissions:',this.$store.state.user.permissions)
		},
    computed: {
      avatar() {
        return this.$store.state.user.avatar
      },
      windowHeight() {
        return uni.getSystemInfoSync().windowHeight - 50
      }
    },
    methods: {
		changePage(e) {
			if(e == 0) {
				this.$tab.reLaunch('/pages/leader')
			}else if (e == 1) {
				// this.$tab.reLaunch('/pages/index')
			}else if (e == 2) {
				this.$tab.reLaunch('/pages/mine/leader')
			}
		},
      handleToInfo() {
        this.$tab.navigateTo('/pages/mine/info/index')
      },
      handleToEditInfo() {
        this.$tab.navigateTo('/pages/mine/info/edit')
      },
      handleToSetting() {
        this.$tab.navigateTo('/pages/mine/setting/index')
      },
      handleToLogin() {
        this.$tab.reLaunch('/pages/login')
      },
      handleToAvatar() {
        this.$tab.navigateTo('/pages/mine/avatar/index')
      },
      handleLogout() {
        this.$modal.confirm('确定注销并退出系统吗？').then(() => {
          this.$store.dispatch('LogOut').then(() => {
            this.$tab.reLaunch('/pages/index')
          })
        })
      },
      handleHelp() {
        this.$tab.navigateTo('/pages/mine/help/index')
      },
      handleAbout() {
        this.$tab.navigateTo('/pages/mine/about/index')
      },
      handleJiaoLiuQun() {
        this.$modal.showToast('QQ群：①xxxxxxxx、②xxxxxxxx')
      },
      handleBuilding() {
        this.$modal.showToast('模块建设中~')
      }
    }
  }
</script>

<style lang="scss">
  page {
    background-color: #f5f6f7;
  }

  .mine-container {
    width: 100%;
    height: 100%;


    .header-section {
      padding: 15px 15px 45px 15px;
      background-color: #2dcabe;
      color: white;

      .login-tip {
        font-size: 18px;
        margin-left: 10px;
      }

      .cu-avatar {
        border: 2px solid #eaeaea;

        .icon {
          font-size: 40px;
        }
      }
			
			.a_image {
				width: 150upx;
				height: 150upx;
				border-color: #def9f5;
				border: 4upx solid;
			}

      .user-info {

				display: flex;
				align-items: center;

        .u_title {
					margin: 30upx;
          font-size: 18px;
          line-height: 30px;
					
					.text{
						padding-top: 22upx;
						padding-bottom: 22upx;
						padding-left: 12upx;
						padding-right: 12upx;
					}
        }
      }
    }

    .content-section {
      position: relative;
      top: -50px;

      .mine-actions {
        margin: 15px 15px;
        padding: 20px 0px;
        border-radius: 8px;
        background-color: white;

        .action-item {
          .icon {
            font-size: 28px;
          }

          .text {
            display: block;
            font-size: 13px;
            margin: 8px 0px;
          }
        }
      }
    }
  }
</style>

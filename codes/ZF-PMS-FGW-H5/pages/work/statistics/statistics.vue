<template>
	<view class="content">
		<view class="upView">
			<view class="upLeftView" @click="test">
				<view class="totalView">总计</view>
				<view class="totalNum">{{totalCount}}次</view>
			</view>
			<view class="line"></view>
			<view class="upRightView">
				<text style="margin-bottom: 5px; margin-top: 15px; font-size: 35rpx;">本月走访{{monthCount}}次</text>
				<text style="margin-bottom: 5px; font-size: 35rpx;">本周走访{{weekCount}}次</text>
				<text style="margin-bottom: 15px; font-size: 35rpx;">今日走访{{todayCount}}次</text>
			</view>
		</view>
		<!-- <uni-search-bar @confirm="search" @input="input" ></uni-search-bar> -->
		<uni-search-bar @confirm="search" v-model="searchValue" @blur="blur" @input="input" @cancel="cancel" @change="change" @clear="clear">
		</uni-search-bar>
		<view>
		    <uni-segmented-control v-if="searchValue.length == 0" :current="current" :values="items" @clickItem="onClickItem" styleType="text" activeColor="#12a98e" customStyleClass="segmentControl"></uni-segmented-control>
		    
			<view class="midView">
				<view v-if="false" class="midUpView">
					<text style="margin-left: 10px; margin-right: 20px; color: #12a98e;">本周</text>
				    <uni-datetime-picker
				        v-model="range"
				        type="daterange"
				        start="2021-3-20"
				        end="2021-5-20"
				        rangeSeparator="至"
				    />
					<text style="margin-left: 20px; margin-right: 10px; color: #12a98e;">周度分析</text>
				</view>
				
				<view v-show="false" class="circleView">
					<l-circularProgress :fontShow="false" bgColor="#f2f2f2" :lineWidth="6" boxWidth="120" boxHeight="120" progressColor="rgb(180, 222, 216)"
						  percent=30 style="position: relative;">
						<view class="centerTxtSpecial" style="position: absolute; left: 50%; top: 50%; transform: translate(-50%, -50%);">
						  <view class="numSpecial">{{interviewRank}}/{{interviewGoal}}户</view>
						  <view class="txtSpecial">走访明细 ></view>
						</view>
					</l-circularProgress>
				</view>
				
			</view>
			
			<view v-if="searchValue.length == 0" class="segContent">
		        <view v-show="current === 0">
		            <view class="itemView" v-for="(item, index) in dayListData" :key="index">
						<view class="cell" @click="openDetail(item.id)">
							<image style="width: 50px;height: 50px; margin-top: 10px; margin-left: 10px; margin-bottom: 10px;" :src="item.attachmentOne" mode="aspectFit"></image>
							<view class="subView">
								<view class="subsubView">
									<text style="font-weight: bold;font-size: 30rpx; margin-right: 10px;">{{item.interviewName}}</text>
									<text>{{item.interviewPhone}}</text>
								</view>
								<text>{{item.interviewBeginTime}}</text>
								<text>{{item.interviewAddr}}</text>
							</view>
						</view>
					</view>
		        </view>
		        <view v-show="current === 1">
		            <view class="itemView" v-for="(item, index) in weekListData" :key="index">
		            	<view class="cell" @click="openDetail(item.id)">
		            		<image style="width: 50px;height: 50px; margin-top: 10px; margin-left: 10px; margin-bottom: 10px;" :src="item.attachmentOne" mode="aspectFit"></image>
		            		<view class="subView">
		            			<view class="subsubView">
		            				<text style="font-weight: bold;font-size: 30rpx; margin-right: 10px;">{{item.interviewName}}</text>
		            				<text>{{item.interviewPhone}}</text>
		            			</view>
		            			<text>{{item.interviewBeginTime}}</text>
		            			<text>{{item.interviewAddr}}</text>
		            		</view>
		            	</view>
		            </view>
		        </view>
		        <view v-show="current === 2">
		            <view class="itemView" v-for="(item, index) in monthListData" :key="index">
		            	<view class="cell" @click="openDetail(item.id)">
		            		<image style="width: 50px;height: 50px; margin-top: 10px; margin-left: 10px; margin-bottom: 10px;" :src="item.attachmentOne" mode="aspectFit"></image>
		            		<view class="subView">
		            			<view class="subsubView">
		            				<text style="font-weight: bold;font-size: 30rpx; margin-right: 10px;">{{item.interviewName}}</text>
		            				<text>{{item.interviewPhone}}</text>
		            			</view>
		            			<text>{{item.interviewBeginTime}}</text>
		            			<text>{{item.interviewAddr}}</text>
		            		</view>
		            	</view>
		            </view>
		        </view>
				<view v-show="current === 3">
				    <view class="itemView" v-for="(item, index) in totalListData" :key="index">
				    	<view class="cell" @click="openDetail(item.id)">
				    		<image style="width: 50px;height: 50px; margin-top: 10px; margin-left: 10px; margin-bottom: 10px;" :src="item.attachmentOne" mode="aspectFit"></image>
				    		<view class="subView">
				    			<view class="subsubView">
				    				<text style="font-weight: bold;font-size: 30rpx; margin-right: 10px;">{{item.interviewName}}</text>
				    				<text>{{item.interviewPhone}}</text>
				    			</view>
				    			<text>{{item.interviewBeginTime}}</text>
				    			<text>{{item.interviewAddr}}</text>
				    		</view>
				    	</view>
				    </view>
				</view>
		    </view>
			
			<view v-else class="itemView" v-for="(item, index) in searchListData" :key="index">
				<view class="cell" @click="openDetail(item.id)">
					<image style="width: 50px;height: 50px; margin-top: 10px; margin-left: 10px; margin-bottom: 10px;" :src="item.attachmentOne" mode="aspectFit"></image>
					<view class="subView">
						<view class="subsubView">
							<text style="font-weight: bold;font-size: 30rpx; margin-right: 10px;">{{item.interviewName}}</text>
							<text>{{item.interviewPhone}}</text>
						</view>
						<text>{{item.interviewBeginTime}}</text>
						<text>{{item.interviewAddr}}</text>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import { getAppCountInfo } from "@/api/work"
	import { getInteviewinfoList } from "@/api/work"
	export default {
		data() {
			return {
				
				searchValue: '',
				
				current:0,
				items:['今日','本周','本月','全部'],
				interviewRank: 30,
				interviewGoal: 50,
				
				totalCount: 50,
				todayCount: 5,
				weekCount: 10,
				monthCount: 30,
				
				searchListData:[
					
				],
				
				totalListData:[
					
				],
				
				dayListData:[
					
				],
				weekListData:[
					
				],
				monthListData:[
					// {
					// 	image:'../../../static/images/scene.png',
					// 	name:'刘俊杰',
					// 	kID:'360122199308125322',
					// 	date:'2024.02.18',
					// 	address:'南昌市 高新区 艾湖路恒大御景'
					// },
					// {
					// 	image:'../../../static/images/scene.png',
					// 	name:'JJ',
					// 	kID:'360122199308125322',
					// 	date:'2024.02.18',
					// 	address:'南昌市 高新区 艾湖路恒大御景'
					// },
					// {
					// 	image:'../../../static/images/scene.png',
					// 	name:'MJ',
					// 	kID:'360122199308125322',
					// 	date:'2024.02.18',
					// 	address:'南昌市 高新区 艾湖路恒大御景'
					// }
				]
			}
		},
		
		onLoad: function (option) {
		    uni.setNavigationBarTitle({ // 使用uni.setNavigationBarTitle函数进行设置
		      title: '统计' // 将title字段设为想要显示的标题内容
		    })
			
			this.interviewRank = option.interviewRank > 0 ? option.interviewRank : 15
			this.interviewGoal = option.interviewGoal > 0 ? option.interviewGoal : 50
			
			this.getAppCountInfo()
			this.getInteviewinfoList()
		},
		methods: {
			onClickItem(e) {
				if (this.current !== e.currentIndex) {
					this.current = e.currentIndex;
				}
				
				
				// let aa = function(0, 0)
				// console.log('======' + aa)
			},
			
			// 打开详情页面
			openDetail(id) {
				this.$tab.navigateTo('/pages/home/change?id='+id)
			},
			
			test(){
				getInteviewinfoList(1, 100,  "",  "", true, '郑先生', this.current+1).then(response => {
				  // this.$modal.msgSuccess("接口测试成功")
					this.weekListData = response.rows
				  console.log('======￥￥￥￥￥接口测试成功'+JSON.stringify(response.rows))
				})
			},
			
			getAppCountInfo() {
				console.log('======******接口调用成功')
				getAppCountInfo().then(response => {
				  
				  this.totalCount = response.data.totalCount > 0 ? response.data.totalCount : 0
				  this.todayCount = response.data.todayCount > 0 ? response.data.todayCount : 0
				  this.weekCount = response.data.weekCount > 0 ? response.data.weekCount : 0
				  this.monthCount = response.data.monthCount > 0 ? response.data.monthCount : 0
				  console.log('======******接口测试成功'+JSON.stringify(response))
				})
			},
			
			getInteviewinfoList() {
				console.log('======接口调用成功')
				
			  getInteviewinfoList(1, 10000,  "",  "", false, '', 4).then(response => {
			    // this.$modal.msgSuccess("接口测试成功")
			  	this.totalListData = response.rows
			    console.log('======接口测试成功'+JSON.stringify(response.rows))
			  })
				
			  getInteviewinfoList(1, 10000,  "",  "", false, '', 1).then(response => {
			    // this.$modal.msgSuccess("接口测试成功")
				this.dayListData = response.rows
				// for (let i = 0; i < response.rows.length; i++) {
				// 	console.log('======接口测试成功'+JSON.stringify(response.rows[i].interviewName))
				// }
				
			    console.log('======接口测试成功'+JSON.stringify(response.rows))
			  })
			  
			  getInteviewinfoList(1, 10000,  "",  "", false, '', 2).then(response => {
			    // this.$modal.msgSuccess("接口测试成功")
			  	this.weekListData = response.rows
			    console.log('======接口测试成功'+JSON.stringify(response.rows))
			  })
			  
			  getInteviewinfoList(1, 10000,  "",  "", false, '', 3).then(response => {
			    // this.$modal.msgSuccess("接口测试成功")
			  	this.monthListData = response.rows
			    console.log('======接口测试成功'+JSON.stringify(response.rows))
			  })
			},
			
			// function(n, type) { // 周
			// 	let now = new Date()
			// 	let day = now.getDay() //返回星期几的某一天;
			// 	if (!type) {
			// 		if (n == 1) {
			// 			let dayNumber = day == 0 ? 0 : 7 - day
			// 			now.setDate(now.getDate() + dayNumber + 1)
			// 		} else if (n == -1) {
			// 			let dayNumber = day == 0 ? 6 : day - 1
			// 			now.setDate(now.getDate() - dayNumber - 7)
			// 		} else {
			// 			let dayNumber = day == 0 ? 6 : day - 1
			// 			now.setDate(now.getDate() - dayNumber)
			// 		}
			// 	} else {
			// 		if (n == 1) {
			// 			let dayNumber = day == 0 ? 0 : 7 - day
			// 			now.setDate(now.getDate() + dayNumber + 1 + 6) // 在周开始的日期上+6天=周结束
			// 		} else if (n == -1) {
			// 			let dayNumber = day == 0 ? 6 : day - 1
			// 			now.setDate(now.getDate() - dayNumber - 7 + 6)
			// 		} else {
			// 			let dayNumber = day == 0 ? 0 : 7 - day
			// 			now.setDate(now.getDate() + dayNumber)
			// 		}
			// 	}
			// 	let date = now.getDate()
			// 	let month = now.getMonth() + 1
			 
			// 	let s = now.getFullYear() + '-' + (month < 10 ? '0' + month : month) + '-' + (date < 10 ? '0' +
			// 		date :
			// 		date)
			// 	return s
			// }
			
			input(res) {
				console.log('----input:' + this.searchValue)//this.current+1
				getInteviewinfoList(1, 10000,  "",  "", true, this.searchValue, 4).then(response => {
				  // this.$modal.msgSuccess("接口测试成功")
					this.searchListData = response.rows
				  console.log('======￥￥￥￥￥接口测试成功'+JSON.stringify(response.rows))
				})
			}
		}
	}
</script>

<style>
	page {
	  background-color: #fff;
	}
	/* upView upLeftView totalView totalNum line upRightView */
	.upView {
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: flex-start;
		background-color: white;
	}
	
	.upLeftView {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		margin-left: 20px;
		
	}
	
	.totalView {
		font-weight: bold;
		font-size: 40rpx;
		margin-bottom: 10px;
	}
	
	.totalNum {
		font-weight: bold;
		font-size: 40rpx;
	}
	
	.line {
		margin-left: 20px;
		margin-right: 30px;
		width: 1px;
		height: 30px;
		background-color: #b2b2b3;
	}
	
	.upRightView {
		display: flex;
		flex-direction: column;
		align-items: flex-start;
		justify-content: center;
	}
	
	.segmentControl {
		background-color: white;
	}
	
	.midView {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		background-color: white;
	}
	
	.midUpView {
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: center;
		width: 750upx;
		margin-top: 10px;
		margin-bottom: 10px;
	}
	
	.circleView {
		display: flex;
		align-items: center;
		justify-content: center;
		width: 150px;
		height: 150px;
	}
	
	.centerTxtSpecial {
		position: absolute;
		top: 50%;
		left: 47%;
		transform: translate(-50%, -50%);
		text-align: center;
	}
	 
	.numSpecial {
		font-size: 30rpx;
		font-family: Arial;
		/* font-weight: bold; */
		color: rgb(86, 162, 151);
	}
	 
	.txtSpecial {
		font-size: 28rpx;
		font-family: PingFang SC;
		font-weight: 400;
		color: rgb(86, 162, 151);
		margin-top: 8rpx;
	}
	
	.itemView {
	  display: flex;
	  flex-direction: column;
	  align-items: flex-start;
	  justify-content: center;
	}
	
	.cell {
	  display: flex;
	  flex-direction: row;
	  align-items: center;
	  justify-content: flex-start;
	  margin-top: 1px;
	  background-color: white;
	  width: 750upx;
		border-width: 0upx 0upx 1upx 0upx;
		border-style: solid;
		border-color: #d8d8d8;
	}
	
	.subView {
	  display: flex;
	  flex-direction: column;
	  align-items: flex-start;
	  justify-content: center;
		margin-left: 10px;
	}
	
	.subsubView {
	  display: flex;
	  flex-direction: row;
	  align-items: center;
	  justify-content: center;
	}

</style>

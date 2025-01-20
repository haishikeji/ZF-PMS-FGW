<template>
	<view>
		<uni-segmented-control :current="current" :values="items" @clickItem="onClickItem" styleType="text" activeColor="#12a98e" customStyleClass="segmentControl"></uni-segmented-control>
		
		<view v-show="current === 0">
			<view v-if="showFlag1" class="myCell">
				<image class="image" style="width: 50px;height: 50px; margin-top: 10px; margin-left: 10px; margin-bottom: 10px;" :src="myAvatar" mode="aspectFit"></image>
				<view class="subView">
					<text style="font-weight: bold;font-size: 30rpx; margin-bottom: 5px;">{{myName}}</text>
					<text>走访{{count1}}次</text>
				</view>
				<text class="rankText">{{rank1 > 0 ? rank1 : "--"}}</text>
			</view>
		    <view class="itemView" v-for="(item, index) in rankList1" :key="index">
				<view class="cell">
					<image class="image" style="width: 50px;height: 50px; margin-top: 10px; margin-left: 10px; margin-bottom: 10px;" :src="item.avatar == '' ? 'https://www.liushaotou.ltd/spring_marketing/profile/avatar/2024/03/01/tx_20240301155230A014.jpg' : item.avatar" mode="aspectFit"></image>
					<view class="subView">
						<text style="font-weight: bold;font-size: 30rpx; margin-bottom: 5px;">{{item.nickName}}</text>
						<text>走访{{item.interviewCount}}次</text>
					</view>
					<image v-if="item.rank===1" style="width: 50px; height: 50px; margin-right: 10px;" src="../../../static/images/rank1.png" mode="aspectFit"></image>
					<image v-else-if="item.rank===2" style="width: 50px; height: 55px; margin-right: 10px;" src="../../../static/images/rank2.png" mode="aspectFit"></image>
					<image v-else-if="item.rank===3" style="width: 50px; height: 55px; margin-right: 10px;" src="../../../static/images/rank3.png" mode="aspectFit"></image>
					<text v-else class="rankText">{{item.rank}}</text>
				</view>
			</view>
		</view>
		<view v-show="current === 1">
			<view v-if="showFlag2" class="myCell">
				<image class="image" style="width: 50px;height: 50px; margin-top: 10px; margin-left: 10px; margin-bottom: 10px;" :src="myAvatar" mode="aspectFit"></image>
				<view class="subView">
					<text style="font-weight: bold;font-size: 30rpx; margin-bottom: 5px;">{{myName}}</text>
					<text>走访{{count2}}次</text>
				</view>
				<text class="rankText">{{rank2 > 0 ? rank2 : "--"}}</text>
			</view>
		    <view class="itemView" v-for="(item, index) in rankList2" :key="index">
				<view class="cell">
					<image class="image" style="width: 50px;height: 50px; margin-top: 10px; margin-left: 10px; margin-bottom: 10px;" :src="item.avatar == '' ? 'https://www.liushaotou.ltd/spring_marketing/profile/avatar/2024/03/01/tx_20240301155230A014.jpg' : item.avatar" mode="aspectFit"></image>
					<view class="subView">
						<text style="font-weight: bold;font-size: 30rpx; margin-bottom: 5px;">{{item.nickName}}</text>
						<text>走访{{item.interviewCount}}次</text>
					</view>
					<image v-if="item.rank===1" style="width: 50px; height: 50px; margin-right: 10px;" src="../../../static/images/rank1.png" mode="aspectFit"></image>
					<image v-else-if="item.rank===2" style="width: 50px; height: 55px; margin-right: 10px;" src="../../../static/images/rank2.png" mode="aspectFit"></image>
					<image v-else-if="item.rank===3" style="width: 50px; height: 55px; margin-right: 10px;" src="../../../static/images/rank3.png" mode="aspectFit"></image>
					<text v-else class="rankText">{{item.rank}}</text>
				</view>
			</view>
		</view>
		<view v-show="current === 2">
			<view v-if="showFlag3" class="myCell">
				<image class="image" style="width: 50px;height: 50px; margin-top: 10px; margin-left: 10px; margin-bottom: 10px;" :src="myAvatar" mode="aspectFit"></image>
				<view class="subView">
					<text style="font-weight: bold;font-size: 30rpx; margin-bottom: 5px;">{{myName}}</text>
					<text>走访{{count3}}次</text>
				</view>
				<text class="rankText">{{rank3 > 0 ? rank3 : "--"}}</text>
			</view>
		    <view class="itemView" v-for="(item, index) in rankList3" :key="index">
				<view class="cell">
					<image class="image" style="width: 50px;height: 50px; margin-top: 10px; margin-left: 10px; margin-bottom: 10px;" :src="item.avatar == '' ? 'https://www.liushaotou.ltd/spring_marketing/profile/avatar/2024/03/01/tx_20240301155230A014.jpg' : item.avatar" mode="aspectFit"></image>
					<view class="subView">
						<text style="font-weight: bold;font-size: 30rpx; margin-bottom: 5px;">{{item.nickName}}</text>
						<text>走访{{item.interviewCount}}次</text>
					</view>
					<image v-if="item.rank===1" style="width: 50px; height: 50px; margin-right: 10px;" src="../../../static/images/rank1.png" mode="aspectFit"></image>
					<image v-else-if="item.rank===2" style="width: 50px; height: 55px; margin-right: 10px;" src="../../../static/images/rank2.png" mode="aspectFit"></image>
					<image v-else-if="item.rank===3" style="width: 50px; height: 55px; margin-right: 10px;" src="../../../static/images/rank3.png" mode="aspectFit"></image>
					<text v-else class="rankText">{{item.rank}}</text>
				</view>
			</view>
		</view>
		<view v-show="current === 3">
			<view v-if="showFlag4" class="myCell">
				<image class="image" style="width: 50px;height: 50px; margin-top: 10px; margin-left: 10px; margin-bottom: 10px;" :src="myAvatar" mode="aspectFit"></image>
				<view class="subView">
					<text style="font-weight: bold;font-size: 30rpx; margin-bottom: 5px;">{{myName}}</text>
					<text>走访{{count4}}次</text>
				</view>
				<text class="rankText">{{rank4 > 0 ? rank4 : "--"}}</text>
			</view>
		    <view class="itemView" v-for="(item, index) in rankList4" :key="index">
				<view class="cell">
					<image class="image" style="width: 50px;height: 50px; margin-top: 10px; margin-left: 10px; margin-bottom: 10px;" :src="item.avatar == '' ? 'https://www.liushaotou.ltd/spring_marketing/profile/avatar/2024/03/01/tx_20240301155230A014.jpg' : item.avatar" mode="aspectFit"></image>
					<view class="subView">
						<text style="font-weight: bold;font-size: 30rpx; margin-bottom: 5px;">{{item.nickName}}</text>
						<text>走访{{item.interviewCount}}次</text>
					</view>
					<image v-if="item.rank===1" style="width: 50px; height: 50px; margin-right: 10px;" src="../../../static/images/rank1.png" mode="aspectFit"></image>
					<image v-else-if="item.rank===2" style="width: 50px; height: 55px; margin-right: 10px;" src="../../../static/images/rank2.png" mode="aspectFit"></image>
					<image v-else-if="item.rank===3" style="width: 50px; height: 55px; margin-right: 10px;" src="../../../static/images/rank3.png" mode="aspectFit"></image>
					<text v-else class="rankText">{{item.rank}}</text>
				</view>
			</view>
		</view>
		<view v-show="current === 4">
			<view v-if="showFlag5" class="myCell">
				<image class="image" style="width: 50px;height: 50px; margin-top: 10px; margin-left: 10px; margin-bottom: 10px;" :src="myAvatar" mode="aspectFit"></image>
				<view class="subView">
					<text style="font-weight: bold;font-size: 30rpx; margin-bottom: 5px;">{{myName}}</text>
					<text>走访{{count5}}次</text>
				</view>
				<text class="rankText">{{rank5 > 0 ? rank5 : "--"}}</text>
			</view>
		    <view class="itemView" v-for="(item, index) in rankList5" :key="index">
				<view class="cell">
					<image class="image" style="width: 50px;height: 50px; margin-top: 10px; margin-left: 10px; margin-bottom: 10px;" :src="item.avatar == '' ? 'https://www.liushaotou.ltd/spring_marketing/profile/avatar/2024/03/01/tx_20240301155230A014.jpg' : item.avatar" mode="aspectFit"></image>
					<view class="subView">
						<text style="font-weight: bold;font-size: 30rpx; margin-bottom: 5px;">{{item.nickName}}</text>
						<text>走访{{item.interviewCount}}次</text>
					</view>
					<image v-if="item.rank===1" style="width: 50px; height: 50px; margin-right: 10px;" src="../../../static/images/rank1.png" mode="aspectFit"></image>
					<image v-else-if="item.rank===2" style="width: 50px; height: 55px; margin-right: 10px;" src="../../../static/images/rank2.png" mode="aspectFit"></image>
					<image v-else-if="item.rank===3" style="width: 50px; height: 55px; margin-right: 10px;" src="../../../static/images/rank3.png" mode="aspectFit"></image>
					<text v-else class="rankText">{{item.rank}}</text>
				</view>
			</view>
		</view>
		
	</view>
</template>

<script>
	import { getInteviewAppRanklist } from "@/api/work"
	export default {
		data() {
			return {
				current:0,
				// '双主官','副所长','队长','社区民警','部门'
				items:['双主官','副所长','队长','社区民警','部门'],
				myAvatar:this.$store.state.user.avatar,
				myName:this.$store.state.user.nickname,
				
				//第一个对象
				rankList1:[],//排名列表
				showFlag1:false,//是否展示自己
				rank1:1,//自己排名
				count1:0,//自己走访次数
				
				//第二个对象
				rankList2:[],//排名列表
				showFlag2:false,//是否展示自己
				rank2:1,//自己排名
				count2:0,//自己走访次数
				
				//第三个对象
				rankList3:[],//排名列表
				showFlag3:false,//是否展示自己
				rank3:1,//自己排名
				count3:0,//自己走访次数
				
				//第四个对象
				rankList4:[],//排名列表
				showFlag4:false,//是否展示自己
				rank4:1,//自己排名
				count4:0,//自己走访次数
				
				//第四个对象
				rankList5:[],//排名列表
				showFlag5:false,//是否展示自己
				rank5:1,//自己排名
				count5:0,//自己走访次数
			}
		},
		onLoad: function (option) {
			this.getInteviewAppRanklist()
		},
		methods: {
			onClickItem(e) {
				if (this.current !== e.currentIndex) {
					this.current = e.currentIndex;
				}
			},
			
			getInteviewAppRanklist() {
				console.log('======接口调用成功')
				
			  getInteviewAppRanklist(2).then(response => {
			  	this.rankList1 = response.data[0].rankList
				this.items[0] = response.data[0].rankName
				this.showFlag1 = response.data[0].showFlag
				this.rank1 = response.data[0].rank
				this.count1 = response.data[0].count
				
				this.rankList2 = response.data[1].rankList
				this.items[1] = response.data[1].rankName
				this.showFlag2 = response.data[1].showFlag
				this.rank2 = response.data[1].rank
				this.count2 = response.data[1].count
				
				this.rankList3 = response.data[2].rankList
				this.items[2] = response.data[2].rankName
				this.showFlag3 = response.data[2].showFlag
				this.rank3 = response.data[2].rank
				this.count3 = response.data[2].count
				
				this.rankList4 = response.data[3].rankList
				this.items[3] = response.data[3].rankName
				this.showFlag4 = response.data[3].showFlag
				this.rank4 = response.data[3].rank
				this.count4 = response.data[3].count
				
				this.rankList5 = response.data[4].rankList
				this.items[4] = response.data[4].rankName
				this.showFlag5 = response.data[4].showFlag
				this.rank5 = response.data[4].rank
				this.count5 = response.data[4].count
			    console.log('======&&&==='+JSON.stringify(response.data))
			  })
			}
		}
	}
</script>

<style>
	page {
	  background-color: #fff;
	}
	
	.itemView {
	  display: flex;
	  flex-direction: column;
	  align-items: flex-start;
	  justify-content: center;
	}
	
	.myCell {
	  display: flex;
	  flex-direction: row;
	  align-items: center;
	  justify-content: flex-start;
	  margin-top: 1px;
	  background-color: white;
	  width: 750upx;
	  margin-bottom: 15px;
		border-width: 0upx 0upx 1upx 0upx;
		border-style: solid;
		border-color: #d8d8d8;
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
	
	.image {
		border-radius: 10px;
	}
	
	.subView {
		display: flex;
		flex-direction: column;
		align-items: flex-start;
		justify-content: center;
		width: 500upx;
		margin-left: 10px;
	}
	
	.rankTest {
		margin-right: 10px;
	}
	
</style>

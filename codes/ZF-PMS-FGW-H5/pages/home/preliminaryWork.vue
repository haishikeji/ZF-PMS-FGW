<template>
	<view class="container">
		<view class="example">
			<view v-for="(item,index) in list" class="cell">
				<view class="left">
					<view>{{item.name}}</view>
					<view style="flex: 1"></view>
					<view>{{item.examine}}</view>
				</view>
				<view class="right" @click="open(item.id,item.type)">
					<view v-if="item.type == 0" style="font-size: 25upx;color: #FF4C46;">未上传</view>
					<view v-else style="font-size: 25upx;color: #1E91D4;">已上传</view>
					<view style="flex: 1;"></view>
					<view>
						<uni-icons type="right" size="25"></uni-icons>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import {getWorkList} from '@/api/home/preliminaryWork.js'
	
	export default {
		data() {
			return {
				projectId: '',
				// list:[],
				list: []
			}
		},
		onLoad(e) {
			this.projectId = e.id
		},
		onShow() {
			setTimeout(()=>{
				getWorkList(this.projectId).then(res=>{
					console.log(res)
					this.list = res.data
				}).catch(err=>{
					console.log(err)
				})
			},100)
		},
		methods: {
			open(id,type) {
				uni.navigateTo({
					url: '/pages/home/putMaterial?id='+id+'&type='+type+'&projectId='+this.projectId
				})
			}
		}
	}
</script>

<style>
	
	.example {
		padding: 15upx;
	}
	
	.cell {
		display: flex;
		align-items: center;
		background-color: white;
		width: 100%;
		border-radius: 10px;
		padding: 20upx;
		margin-top: 30upx;
	}
	
	.left {
		width: 75%;
		display: flex;
		align-items: center;
	}
	
	.right {
		width: 25%;
		display: flex;
		align-items: center;
		padding-left: 30upx;
	}
</style>
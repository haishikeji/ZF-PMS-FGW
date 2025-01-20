<template>
	<view class="container">
			<view class="example">
				<uni-forms ref="baseForm" :modelValue="baseFormData"  :rules="rules">
					
					<uni-forms-item label-width="200upx" label="审批事项"  required>
						<uni-easyinput v-model="baseFormData.approvedItems" :styles="styles" />
					</uni-forms-item>
					<uni-forms-item label-width="200upx" label="办理状态"  required>
						<uni-data-select v-model="baseFormData.handlingStatusInt" :localdata="handlingList" :clear="false"></uni-data-select>
					</uni-forms-item>
					<uni-forms-item label-width="200upx" label="批复单位"  required>
						<uni-data-select v-model="depIndex" :localdata="departmentList" :clear="false" @change="changeDep"></uni-data-select>
					</uni-forms-item>
					<uni-forms-item label-width="200upx" label="计划批复时间"  required>
						<uni-datetime-picker type="date" :clear-icon="false" v-model="baseFormData.scheduledApprovalTime"  />
					</uni-forms-item>
					<uni-forms-item label-width="200upx" label="实际批复时间"  required>
						<uni-datetime-picker type="date" :clear-icon="false" v-model="baseFormData.actualApprovalTime"  />	
					</uni-forms-item>
					<uni-forms-item label-width="200upx" label="批复文号"  required>
						<uni-easyinput v-model="baseFormData.approvalNumber" :styles="styles"/>		
					</uni-forms-item>
					
					<view class="line"></view>
					
					<uni-forms-item label-width="10upx">
						<uni-file-picker :del-icon="false" v-model="files" return-type="array" @select="uploadFile" @delete="delFile" limit="2" file-mediatype="all" title="批复附件(最多上传2份)"></uni-file-picker>
					</uni-forms-item>
					
					
				</uni-forms>
				<button type="primary" @click="putInfo" :disabled="flag">提交</button>
			</view>
	
	</view>
</template>

<script>
	import {uploadCommonFile} from '@/api/system/user.js'
	import {getWorkInfo,putFormData} from '@/api/home/putMaterial.js'
	import { getDepartmentList} from '@/api/putInfo.js'
	
	export default {
		data() {
			return {
				// 文件上传标识
				file: 0,
				// 提交标识
				flag: false,
				// 输入框样式
				styles: {
					disableColor: '#fff'
				},
				depIndex: 0,
				baseFormData: {
					id: '',
					approvalFileType: '',
					applyProjectId: '',
					approvedItems: '',
					handlingStatusInt: 0,
					// 部门id
					approvedUnitId: '',
					// 部门名称
					approvedUnitName: '',
					actualApprovalTime: '',
					scheduledApprovalTime: '',
					approvalNumber: '',
					fileUploadInfoList: []
				},
				// 办理状态
				handlingList: [
					{
						text: '未办理',
						value: 0
					},
					{
						text: '已办理',
						value: 1
					},
				],
				// 职能部门列表
				departmentList: [
					{
						text: '数据获取中',
						value: '0',
					},
				],
				rules: {
					num:{
						rules:[{
							required: true,
							errorMessage: '请输入内容',
						}]
					},
				},
				files: []
			}
		},
		onLoad(e) {
			console.log(e)
			this.baseFormData.applyProjectId = e.projectId
			this.baseFormData.approvalFileType = e.id
			
			// 获取职能部门列表
			getDepartmentList().then(res=>{
				console.log(res)
				this.departmentList = res.data
				
				// 已办理
				if (e.type == '1') {
					getWorkInfo(e.projectId,e.id).then(res=>{
						console.log(res)
						this.baseFormData = res.data
						
						for (var i = 0; i < this.departmentList.length; i++) {
							if (this.departmentList[i].id == this.baseFormData.approvedUnitId) {
								this.depIndex = this.departmentList[i].value
							}
						}
						
						if (this.baseFormData.fileUploadInfoList != null) {
							let arr = this.baseFormData.fileUploadInfoList
							for (let i = 0; i < arr.length; i++) {
								const pic = {
									url: arr[i].filePath,
									name: arr[i].fileName,
									extname: arr[i].fileName.split('.')[1]
								}
								
								this.files.push(pic)
							}
						}
						
					}).catch(err=>{
						console.log(err)
					})
				}
			}).catch(err=>{
				console.log(err)
			})
			
			
		},
		methods: {
			// 选择部门
			changeDep(e) {
				console.log(e)
				this.baseFormData.approvedUnitId = this.departmentList[e-1].id
				this.baseFormData.approvedUnitName = this.departmentList[e-1].text
			},
			uploadFile(e) {
				this.file = this.file + 1
				console.log(e)
				const tempFilePaths = e.tempFilePaths;
				//获取文件临时路径
				const Url=tempFilePaths[0]
				let data = {name: 'file', filePath: Url}
				
				uploadCommonFile(data).then(res => {
					const pic = {
						url: res.url,
						name: res.originalFilename,
						extname: res.originalFilename.split('.')[1]
					}
					
					this.files.push(pic)
					console.log(res)
					this.file = this.file - 1
				}).catch(err=>{
					console.log(err)
					this.file = this.file - 1
				})
			},
			// 等待文件上传函数
			waitImg(){
				return new Promise((resolve,reject)=>{
					if(this.image > 0){
						setTimeout((res)=>{
							this.waitImg().then(()=>{
								resolve()
							})
						},100)
					}else{
						resolve()
					}
				})
			},
			delFile(e) {
				console.log(e)
			},
			// 提交数据
			putInfo() {
				uni.showLoading({
					title: '信息提交中...',
					mask: true
				})
				this.flag = true
				
				
				this.waitImg().then(()=>{
						// 提交信息
						this.$refs.baseForm.validate().then(res=>{
							console.log('表单数据信息：', res);
								
								// 处理保存文件对象
								let filelist = []
								for (let i = 0; i < this.files.length; i++) {
									const file = {
										fileName: this.files[i].name,
										fileSize: '0',
										filePath: this.files[i].url
									}
									
									filelist.push(file)
								}
								
								this.baseFormData.fileUploadInfoList = filelist
								
								putFormData(this.baseFormData).then(response => {
									console.log(response)
									uni.hideLoading()
									this.$modal.msgSuccess("提交成功")
									
									setTimeout(()=>{
										uni.navigateBack({
											delta: 1
										});
									},1000)
									
								}).catch(err=>{
									this.flag = false
									uni.hideLoading()
									console.log('提交失败',err)
								})
								
							}).catch(err =>{
								this.flag = false
								uni.hideLoading()
								console.log('表单错误信息：', err);
							})
				})
				
			},
		}
	}
</script>

<style>
	page {
		background-color: white;
	}
	
	.example {
	padding: 15upx;
	}
	
	.line {
		width: 100%;
		margin-top: 27upx;
		margin-bottom: 27upx;
		border-width: 2upx 0upx 0upx 0upx;
		border-style: solid;
		border-color: gainsboro;
	}
</style>
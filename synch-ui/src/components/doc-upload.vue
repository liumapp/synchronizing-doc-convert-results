/**
 * @file doc-upload.vue
 * @author liumapp
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/3/18
 */
<template>
  <div>
    <br>
    <Row>
      <Col span="8" offset="8">
        <Upload
          multiple
          type="drag"
          :action="uploadUrl"
          :before-upload="handleFileToBase64">
          <div style="padding: 20px 0">
            <Icon type="ios-cloud-upload" size="52" style="color: #3399ff"></Icon>
            <p>Click or drag files here to upload</p>
          </div>
        </Upload>
      </Col>
    </Row>

  </div>
</template>
<script>
import util from '@/libs/util'
export default {
  name: 'doc-upload',
  data () {
    return {
      uploadUrl: util.ajaxUrl + "/upload/base64",
    }
  },
  methods: {
    handleFileToBase64 (file) {
      console.log(file);
      return false;
    }
  }
}
</script>

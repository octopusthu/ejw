# Request Wrapper Sample

An HttpServletRequestWrapper that modify the incoming HttpServletRequest in an AOP way, aiming to make the modifications TRANSPARENT to the underlying Spring components (controllers, services, etc).  

## Use cases

- Password encryption

### 解决方案
 - 在配置文件配置需要加密解密的url和parameter
 - 自定义filter,拦截到需要解密的url,dofilter
 - 定义HttpRequestWrapper，处理解密
 -  filterChain(HttpRequestWrapper, response);
 
### 参考文档
 - 
>  https://stackoverflow.com/questions/681263/modify-httpservletrequest-body
 - 
>  https://stackoverflow.com/questions/50932518/how-to-modify-request-body-before-reaching-controller-in-spring-boot

### 踩的坑
 - @RequestParam  和 @RequestBody 区别，以及HttpRequestWrapper分别需要@Override的方法
   - @RequestParam ：@getParameter @getParameterValues @getParameterMap
   - @RequestBody : @getReader @getInputStream
 - spring security自定义filter重复执行问题
   - 
>  https://segmentfault.com/a/1190000012173419
 - ServletRequest中getReader()和getInputStream()只能调用一次
   - 
>  https://www.cnblogs.com/zj0208/p/6214576.html

### 需要修改的点
 - 配置文件配置属性的分隔符 
 - url  和  url/ 的匹配
 - ……

### 延伸和适配性
 - 加密和机密算法的可配置
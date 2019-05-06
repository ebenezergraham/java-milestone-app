<%@ page contentType="text/html;charset=UTF-8" %>

<div id="${project.getId()}" class="project-box m-3">
  <a href="/project?id=${project.getId()}" class="project card">
    <c:out value="${project.getTitle()}"/>
  </a>
  <div class="option-box">
    <i
        class="options rounded-circle m-2 text-white fa fa-trash btn-delete-project"
        title="delete"></i>
    <i class="options rounded-circle m-2 text-white fa fa-share share-project"
       title="share"></i>
  </div>
</div>

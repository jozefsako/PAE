/*
 * Listener variable
 * that triggers a function
 * on change.
 */
v = {
  aInternal: 10,
  aListener: function (val) {
  },
  set a(val) {
    this.aInternal = val;
    this.aListener(val);
  },
  get a() {
    return this.aInternal;
  },
  registerListener: function (listener) {
    this.aListener = listener;
  }
};

/*
 * setCookie
 * init new coockie
 * @param : name
 * @param : value
 * @param : age
 */
function setCookie(name, value, days) {
  let expires = "";
  if (days) {
    let date = new Date();
    date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
    expires = "; expires=" + date.toUTCString();
  }
  document.cookie = name + "=" + (value || "") + expires + "; path=/";
}

/*
 * getCookie
 * get the value of the requested coockie
 * @param : name
 */
function getCookie(name) {
  let nameEQ = name + "=";
  let ca = document.cookie.split(';');
  for (let i = 0; i < ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) === ' ') {
      c = c.substring(1, c.length);
    }
    if (c.indexOf(nameEQ) === 0) {
      return c.substring(nameEQ.length, c.length);
    }
  }
  return null;
}

/*
 *  Delete cookie
 *  @param : name
 */
function eraseCookie(name) {
  document.cookie = name + '=; Max-Age=-99999999;';
}

/*
 * isIterable
 * Checks : null and undefined
 * @param : obj
 */
function isIterable(obj) {
  if (obj == null) {
    return false;
  }
  return typeof obj[Symbol.iterator] === 'function';
}

/*
 * getCheckBox
 * @param : isChecked
 * return a customized checkbox
 */
function getCheckBox(isChecked) {
  let checkboxHtml = "<div class=\"form-check\">"
      + "<input class=\"form-check-input\" type=\"checkbox\" ";
  if (isChecked === true) {
    checkboxHtml += "checked />";
  } else {
    checkboxHtml += " />";
  }
  checkboxHtml += "</div>";
  return checkboxHtml;
}

/*
 * infoButton
 * return the html of infoButton
 */
function infoButton(id) {
  return "<a id='" + id
      + "' href=\"\" class=\"btn btn-info btn-circle btn-sm\">"
      + "<i class=\"fas fa-info-circle\"></i>"
      + "</a>";
}

/*
 * successButton
 * return the html of successButton
 */
function successButton(id) {
  return "<a id='" + id
      + "' href=\"\" class=\"btn btn-success btn-circle btn-sm\">"
      + "<i class=\"fas fa-check\"></i>"
      + "</a>";
}

/*
 * isNull
 * checks if the object is defined
 * @param : object
 * return true if the object is null
 */
function isNull(object) {
  if (object === "") {
    return true;
  }
  if (object === " ") {
    return true;
  }
  if (object === undefined) {
    return true;
  }
  return object.length === 0;

}

function checkNull(element) {
  return isNull(element) ? "" : element;
}

function createTableRow(element) {
  return "<td>" + element + "</td>";
}

function FormToJSON(form){
        let objectJSON = {};
        form.find('input[type=text], input[type=password], textarea, select[multiple]').each(function(i, el){
            objectJSON[$(el).attr('name')] = $(el).val();
        });

        form.find('input[type=number]').each(function(i, el){
	        if($(el).val() === ""){
		        objectJSON[$(el).attr('name')] = 0;
	        }else{
		        objectJSON[$(el).attr('name')] = parseFloat($(el).val());
	        }
        });

        form.find('input[type=radio]').each(function(i, el){
            if($(el).is(':checked')){
                objectJSON[$(el).attr('name')] = $(el).attr('value');
            }else if(objectJSON[$(el).attr('name')] === undefined) {
                objectJSON[$(el).attr('name')] = null;
            }
        });

        form.find('input[type=checkbox]').each(function(i,el){
            if($(el).is(':checked')){
                objectJSON[$(el).attr('name')] = true;
            }else{
                objectJSON[$(el).attr('name')] = false;
            }
        });
        
        form.find('select').each(function(i, el){
	        if($(el).val() === null){
		        objectJSON[$(el).attr('name')] = "";
	        }else{
		        objectJSON[$(el).attr('name')] = $(el).val();
	        }
        });
        form.find('input[type=date]').each((i, el) =>{
          let birthdate = [];
          let date = new Date($('#encode_birthdate').val());
          console.log(date);
          birthdate.push(date.getFullYear());
          birthdate.push(date.getMonth() + 1);
          birthdate.push(date.getDate());
          if (date){
            objectJSON[$(el).attr('name')] = birthdate;
          }
        });
        

       return JSON.stringify(objectJSON);
    }

 function JSONToForm(json, idForm){
    let objectJSON = json;
    for(k in objectJSON){
        let el = $(idForm).find('[name='+k+"]");
        if(el.is('input[type=number], select')){
            $(el).val(objectJSON[k]);
        }else if(el.is('input[type=text], input[type=password], textarea')){
            $(el).val(objectJSON[k]);
        }else if(el.is('input[type=radio]')){
            $(el).filter('[value="' + objectJSON[k] +'"]').prop('checked', true);
        }else if(el.is('input[type=checkbox]')){
            if(objectJSON[k]){
                $(el).prop('checked', true);
            }
        }
    }
    
}

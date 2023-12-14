// KooValidChecker.js
// 개발자가 의도한 대로 하지 않으면 true값
// (뭔가 문제가 되면 true, 정상적이면 false인 컨셉)

// <input>을 넣었을 때
//      아무것도 안썼으면 true, 뭐라도 썼으면 false
function isEmpty(input) {
    return (!input.value);
}

// <input>, 글자수 넣었을 때
//      그 글자 수보다 적으면 true, 그 글자 수 이상이면 false
function atLeastLetter(input, len) {
    return (input.value.length < len);
}

function isNumlengthCheck(input, len) {
	return (input.value.length != len);
}

// <input>넣엇을 때
//      한글 / 특수문자가 적혀있다면 true, 없다면 false
//      일반적으로 한국어를 못쓰게 할 때 사용
function containsAnother(input) {
    let pass = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ@._";

    let iv = input.value;
    for(let i = 0; i < iv.length; i++) {
        if(pass.indexOf(iv[i]) == -1) { // pass에 있는 내용 중에 내가 쓴 내용이 없으면
            return true;
        }
    }
    return false;
}

function onlyEng(input) {
    let pass = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    let iv = input.value;
    for(let i = 0; i < iv.length; i++) {
        if(pass.indexOf(iv[i]) == -1) { // pass에 있는 내용 중에 내가 쓴 내용이 없으면
            return true;
        }
    }
    return false;
}

// <input> x2 넣었을 때 (pw, pw확인)
//      내용이 다르면 true, 같으면 false
function notEqualPw(input1, input2) {
    return (input1.value !== input2.value);
}

// <input>, 문자열 세트 넣엇을 때
//      없으면 true, 있으면 false(입력한 pw에 지정한 문자열이 없는 경우)
function notContains(input, passSet) {
    let iv = input.value;
    for(let i = 0; i < passSet.length; i++) {
        if(iv.indexOf(passSet[i]) !== -1) {
            return false;
        }
    }
    return true;
}

// <input>넣었을 때
//      숫자 아닌게 있으면 true, 숫자만 있으면 false
function isNotNumber(input) {
    return isNaN(input.value);
}

// <input>, 확장자 넣었을 때
//      확장자명 아니면 true, 맞으면 false
function isNotType(input, type) {
    // .gif, .png, ...
    type = "." + type;
    return (input.value.indexOf(type) == -1);
}
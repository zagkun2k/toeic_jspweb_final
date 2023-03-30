$(document).ready(() => {
    handleCheckAll('checkAll');
    handleDeleteBtnShow('deleteAll');
    handleAutoCheckAll('checkAll');
})

const handleCheckAll = (id) => {
    const checkAllElement = $('#' + id);
    const checkTableElement = checkAllElement.closest('table').find('tbody input[type=checkbox]');
    checkAllElement.on('change', () => {
        // console.log(checkAllElement.prop('checked'));
        if (checkAllElement.prop('checked')) {
            checkTableElement.prop('checked', true);
        } else {
            checkTableElement.prop('checked', false);
        }
    })
}

const handleDeleteBtnShow = (id) => {
    let checkBoxElement = $('input[type=checkbox]');
    checkBoxElement.click(function () {
        let presentElement = $(this);
        if (presentElement.attr('id') === 'checkAll' && presentElement.prop('checked') === false) {
            presentElement.closest('table').find('tbody input[type=checkbox]').prop('checked', false);
        }
        if ($('input[type=checkbox]:checked').length > 0) {
            $('#' + id).prop('disabled', false);
        } else {
            $('#' + id).prop('disabled', true);
        }
    });
}

const handleAutoCheckAll = (id) => {
    let checkBoxTableElement = $('#' + id).closest('table').find('tbody input[type=checkbox]');
    let tableCheckBoxLength = checkBoxTableElement.length;
    checkBoxTableElement.each(function () {
        checkBoxTableElement.on('change', function () {
            const haveCheckedBoxTableElement = $('#' + id).closest('table').find('tbody input[type=checkbox]:checked');
            const checkedInputCheckBoxLength = haveCheckedBoxTableElement.length;

            if (checkedInputCheckBoxLength === tableCheckBoxLength) {
                $('#' + id).prop('checked', true);
            } else {
                $('#' + id).prop('checked', false);
            }
        })
    })
}
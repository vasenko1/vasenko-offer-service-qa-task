export function fillTextField(id: string, value: string, isMaskedInput?: boolean) {
    cy.get('body').then($body => {
        if ($body.find('input[id="' + id + '"]').length > 0) {
            cy.get('input[id="' + id + '"]')
                .clear()
                .type(value);
        } else if ($body.find('textarea[id="' + id + '"]').length > 0) {
            cy.get('textarea[id="' + id + '"]')
                .clear()
                .type(value);
        } else if ($body.find('[id="' + id + '"]').find('input').length > 0) {
            if (isMaskedInput) {
                $body
                    .find('[id="' + id + '"]')
                    .find('input')
                    .val(value);
            }

            cy.get('[id="' + id + '"]')
                .find('input')
                .clear()
                .type(value);
        }
    });
}

export function clickButton(caption: string): void {
    cy.get('button')
        .contains(caption)
        .click();
}

export function clickBySelector(selector: string): void {
    cy.get(selector)
        .click();
}

export function clickCyButtonRow(id: string, row: number) {
    cy.get('[data-cy=' + id + ']')
        .eq(row)
        .click();
}

export function getActiveModal() {
    return cy.get('.p-dialog-visible').last();
}

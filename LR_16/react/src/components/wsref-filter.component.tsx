export function WsrefFilterComponent({filter, setFilter, submit, onClose}: {
    filter: string,
    setFilter: (value: (((prevState: string) => string) | string)) => void,
    submit: () => Promise<void>,
    onClose: () => void
}) {
    return (
        <fieldset>
            <div className={"flex"}>
                <input name={"filter"} value={filter} placeholder={"key-word, key-word, ..."}
                       onChange={(e: any) => setFilter(e.target.value)}/>
            </div>
            <div>
                <button onClick={async (e: any) => {
                    await submit();
                    onClose();
                }}>OK
                </button>
                <button onClick={(e: any) => {
                    onClose();
                }}>Cancel
                </button>
            </div>
        </fieldset>
    );
}